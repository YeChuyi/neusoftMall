package com.neusoft.commodityCenter.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.commodityCenter.dao.*;
import com.neusoft.commodityCenter.service.CommodityCenterService;
import com.neusoft.entity.collect.CollectDetailVO;
import com.neusoft.entity.commodityCenter.*;
import com.neusoft.entity.collect.Collect;
import com.neusoft.entity.commodityCenter.request.CenterBasePage;
import com.neusoft.entity.common.Base.PageVo;
import com.neusoft.entity.common.response.AppResponse;
import com.neusoft.entity.goods.Commodity;
import com.neusoft.entity.goods.CommodityPicVo;
import com.neusoft.entity.order.Order;
import com.neusoft.entity.order.OrderDetail;
import com.neusoft.entity.shop.Shop;
import com.neusoft.util.StringUtil;
import com.neusoft.util.UUIDUtil;
import io.swagger.models.auth.In;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * 功能：商品中心业务实现类
 * 作成者：林进华
 * 作成时间：2019/4/21
 */
@Service
public class CommodityCenterServiceImpl implements CommodityCenterService {

    @Resource
    private CommodityDao commodityDao;
    @Resource
    private ShopDao shopDao;
    @Resource
    private CommodityPicDao commodityPicDao;
    @Resource
    private CollecDao collecDao;
    @Resource
    private OrderDao orderDao;
    @Resource
    private OrderDetailDao orderDetailDao;

    /**
     * 参数功能：商品状态
     * COMMODITY_IS_SOLD: 未上架状态
     * COMMODITY_IS_LACK: 缺货状态
     * COMMODITY_IS_LACK_INVENTORY: 库存不足状态
     * SUCCESS: 操作成功
     */
    private static final int COMMODITY_IS_SOLD = -3,
                               COMMODITY_IS_LACK = -2,
                               COMMODITY_IS_LACK_INVENTORY = -1,
                               SUCCESS = 1;


    /**
     * @Dept：第四组
     * @Description：商品中心商品查询
     * @Author：林进华
     * @Date: 2019/4/20
     * @Param：centerBasePageVo 商品中心分页信息
     * @Return：com.neusoft.common.response.AppResponse
     */
    @Override
    public AppResponse getCommodityList(CenterBasePage centerBasePage) {
        // 分页
        PageHelper.startPage(centerBasePage.getPageNum(), centerBasePage.getPageSize());
        // 查询符合条件的记录数
        List<Commodity> commodityList = commodityDao.getCategoryList(centerBasePage);
        PageVo pageVo = new PageVo();
        pageVo.setList(commodityList);
        // 使用PageInfo得到分页的详细信息，如总条数等
        pageVo.setTotalRecords((int) new PageInfo<>(commodityList).getTotal());
        return AppResponse.success("查询成功", pageVo);
    }

    /**
     * @Dept：第四组
     * @Description：添加商品到购物车
     * @Author：林进华
     * @Date: 2019/4/20
     * @Param：shopVo 购物车信息
     * @Return：com.neusoft.common.response.AppResponse
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public AppResponse addShoppingCart(Shop shop) {
        AppResponse appResponse = AppResponse.success("添加成功");
        // 检查商品数量是否小于0
        int commodityCount = Integer.parseInt(shop.getShopNumber());
        if(commodityCount <= 0) {
            appResponse = AppResponse.bizError("添加失败，商品数量异常");
        }else {
            // 生成订单id
            String id = UUIDUtil.uuidStr();
            shop.setShopId(id);
            // 构建商品明细参数
            CommodityCenterDetail args = new CommodityCenterDetail();
            args.setCommodityId(shop.getCommodityId());
            // 查询商品明细，以便得到商品的库存，是否上架，是否缺货等信息
            CommodityCenterDetail commodityCenterDetail = commodityDao.getCommodityCenterDetail(args);
            // 得到商品库存
            int commodityInventory = Integer.parseInt(commodityCenterDetail.getCommodityInventory());
            //是否上架1是0否
            if("0".equals(commodityCenterDetail.getCommodityIsSold())) {
                appResponse = AppResponse.bizError("添加失败，商品未上架");
            //是否缺货1是0否
            }else if("1".equals(commodityCenterDetail.getCommodityIsLack())) {
                appResponse = AppResponse.bizError("添加失败，商品缺货中");
            //库存判断
            }else if(commodityInventory - commodityCount < 0) {
                appResponse = AppResponse.bizError("添加失败，库存不足");
            //购物车添加
            }else {
                int count = shopDao.addShoppingCart(shop);
                // 添加失败
                if(count == 0) {
                    appResponse = AppResponse.bizError("添加失败，请重新添加");
                }
            }
        }
        return appResponse;
    }

    /**
     * @Dept：第四组
     * @Description：得到商品详情
     * @Author：林进华
     * @Date: 2019/4/20
     * @Param：commodityCenterDetailVO 商品中心详情信息
     * @Return：com.neusoft.common.response.AppResponse
     */
    @Override
    public AppResponse getCommodityCenterDetail(CommodityCenterDetail commodityCenterDetail) {
        AppResponse appResponse;
        // 查询商品明细
        CommodityCenterDetail result = commodityDao.getCommodityCenterDetail(commodityCenterDetail);
        if(result != null) {
            // 构建商品参数,设置商品id
            Commodity args = new Commodity();
            args.setCommodityId(commodityCenterDetail.getCommodityId());
            // 查询商品的图片集合
            List<CommodityPicVo> commodityPicVoList = commodityPicDao.getPictureList(args);
            result.setPictureList(commodityPicVoList);
            // 构建返回对象
            CommodityCenterDetailVO commodityCenterDetailVO = new CommodityCenterDetailVO();
            // 属性值拷贝
            BeanUtils.copyProperties(result, commodityCenterDetailVO);
            appResponse = AppResponse.success("查询成功", commodityCenterDetailVO);
        }else {
            appResponse = AppResponse.bizError("查询错误");
        }
        return appResponse;
    }

    /**
     * @Dept：第四组
     * @Description：同类商品推荐集合
     * @Author：林进华
     * @Date: 2019/4/20
     * @Param：commodityVO 商品信息
     * @Return：com.neusoft.common.response.AppResponse
     */
    @Override
    public AppResponse getCommodityCenterSimilar(Commodity commodity) {
        AppResponse appResponse;
        // 查询同类商品集合
        List<Commodity> result = commodityDao.getCommodityCenterSimilar(commodity);
        if(result == null || result.isEmpty()) {
            appResponse = AppResponse.bizError("没有同类商品");
        }else {
            appResponse = AppResponse.success("查询成功", result);
        }
        return appResponse;
    }

    /**
     * @Dept：第四组
     * @Description：交易记录
     * @Author：林进华
     * @Date: 2019/4/20
     * @Param：commodityVO 商品信息
     * @Return：com.neusoft.common.response.AppResponse
     */
    @Override
    public AppResponse getCommodityCenterTradin(Commodity commodity) {
        AppResponse appResponse;
        // 得到交易记录集合
        List<CommodityCenterTradingVO> result = commodityDao.getCommodityCenterTrading(commodity);
        if(result == null || result.size() <= 0) {
            appResponse = AppResponse.bizError("没有交易记录");
        }else {
            appResponse = AppResponse.success("查询成功", result);
        }
        return appResponse;
    }

    /**
     * @Dept：第四组
     * @Description：立即购买
     * @Author：林进华
     * @Date: 2019/4/20
     * @Param：commodityBuyNowVO 立即购买信息
     * @Return：com.neusoft.common.response.AppResponse
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public AppResponse getCommodityBuyNow(CommodityBuyNow commodityBuyNow) {
        AppResponse appResponse;
        int commodityCount = Integer.parseInt(commodityBuyNow.getCommodityNum());
        // 检查商品数量是否小于0
        if(commodityCount <= 0) {
            appResponse = AppResponse.bizError("失败，商品数量不能小于0,请重新添加");
        }else {
            // 商品状态检查
            int status = checkCommodityStatus(commodityBuyNow.getCommodityId(), commodityBuyNow.getCommodityNum());
            // 商品未上架
            if(status == COMMODITY_IS_SOLD){
                appResponse = AppResponse.bizError("失败，商品未上架");
            // 商品缺货
            }else if(status == COMMODITY_IS_LACK) {
                appResponse = AppResponse.bizError("失败，商品缺货中");
            // 商品库存不足
            }else if(status == COMMODITY_IS_LACK_INVENTORY){
                appResponse = AppResponse.bizError("失败，库存不足");
            }else {
                // 得到立即购买的实体
                CommodityBuyNowVO commodityBuyNowVO = commodityDao.getCommodityBuyNow(commodityBuyNow);
                return AppResponse.success("成功", commodityBuyNowVO);
            }
        }
        return appResponse;
    }

    /**
     * @Dept：第四组
     * @Description：商品状态检查，检查商品是否未上架，缺货，库存不足等
     * @Author：林进华
     * @Date: 2019/4/20
     * @Param：collectVoList 收藏列表集合信息
     * @Return：int
     */
    private int checkCommodityStatus(String commodityId, String buyNum) {
        int status = SUCCESS;
        // 构建商品明细参数
        CommodityCenterDetail args = new CommodityCenterDetail();
        args.setCommodityId(commodityId);
        args.setCommodityInventory(buyNum);
        CommodityCenterDetail commodityCenterDetail = commodityDao.getCommodityCenterDetail(args);
        // 缺货、库存、商品未上架检查
        int commodityInventory = Integer.parseInt(commodityCenterDetail.getCommodityInventory());
        int buyCommodityNum = Integer.parseInt(buyNum);
        //上架检查
        if("0".equals(commodityCenterDetail.getCommodityIsSold())) {
            status = COMMODITY_IS_SOLD;
        //缺货检查
        }else if("1".equals(commodityCenterDetail.getCommodityIsLack())) {
            status = COMMODITY_IS_LACK;
        //库存检查
        }else if(commodityInventory - buyCommodityNum < 0) {
            status = COMMODITY_IS_LACK_INVENTORY;
        }
        return status;
    }


    /**
     * @Dept：第四组
     * @Description：添加收藏/取消收藏
     * @Author：林进华
     * @Date: 2019/4/20
     * @Param：collectVoList 收藏列表集合信息
     * @Return：com.neusoft.common.response.AppResponse
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public AppResponse commodityCollection(List<Collect> collectList) {
        AppResponse appResponse = AppResponse.bizError("操作失败");
        // 空集合判断
        if(collectList == null || collectList.isEmpty()) {
            appResponse = AppResponse.bizError("操作列表不能为空");
            return appResponse;
        }
        // 获取集合的第一个对象，判断其是否是收藏操作
        if("1".equals(collectList.get(0).getCollectFlag())) {
            // 添加执行收藏操作的主函数
            int status = doSuccessCommodityCollection(collectList);
            // 判断是否已收藏，如果收藏了则不会再次进行收藏操作
            if(status == 0) {
                appResponse = AppResponse.success("已收藏");
            }else if(status > 0) {
                appResponse = AppResponse.success("收藏成功");
            }

        }
        // 获取集合的第一个对象，判断其是否是取消收藏操作
        if("0".equals(collectList.get(0).getCollectFlag())) {
            // 取消收藏操作的主函数
            int status = doFailCommodityCollection(collectList);
            if(status > 0) {
                appResponse = AppResponse.success("取消成功");
            }
        }
        return appResponse;
    }

    /**
     * @Dept：第四组
     * @Description：收藏操作的主函数
     * @Author：林进华
     * @Date: 2019/4/20
     * @Param：collectVoList 收藏列表集合信息
     * @Return：int 收藏成功的数目
     */
    private int doSuccessCommodityCollection(List<Collect> collectList) {
        // 新建一个移除集合，移除那些已收藏的对象
        List<Collect> removeCollect = new ArrayList<>();
        for(Collect collect : collectList) {
            // 判断是否已收藏
            if(collecDao.getIsCollect(collect) > 0) {
                removeCollect.add(collect);
                // 跳出这次循环，执行下次循环
                continue;
            }
            // 设置收藏对象主键
            String id = UUIDUtil.uuidStr();
            collect.setCollectId(id);
        }
        // 移除已收藏的对象
        collectList.removeAll(removeCollect);
        // 判断是否全部已收藏，是的话返回0，代表没有可执行的对象，否则则进行收藏操作
        return collectList.isEmpty() ? 0 : collecDao.addCommodityCollection(collectList);
    }

    /**
     * @Dept：第四组
     * @Description：取消收藏操作的主函数
     * @Author：林进华
     * @Date: 2019/4/20
     * @Param：collectVoList 收藏列表集合信息
     * @Return：int 取消成功的数目
     */
    private int doFailCommodityCollection(List<Collect> collectList) {
        // 循环对象，为每一个对象设置id
        for(Collect collect : collectList) {
            String id = UUIDUtil.uuidStr();
            collect.setCollectId(id);
        }
        return collecDao.deleteCommodityCollection(collectList);
    }

    /**
     * @Dept：第四组
     * @Description：提交订单
     * @Author：林进华
     * @Date: 2019/4/20
     * @Param：orderVo 订单信息
     * @Return：com.neusoft.common.response.AppResponse
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public AppResponse addOrder(Order order) {
        AppResponse appResponse = AppResponse.bizError("支付失败");
        // 商品状态
        int status = SUCCESS;

        // 设置订单编号和订单id
        String orderNumber = StringUtil.getCommonCode(2);
        String orderId = UUIDUtil.uuidStr();
        order.setOrderId(orderId).setOrderNumber(orderNumber);

        // 商品状态检查和设置商品明细的订单ID和主键ID
        List<OrderDetail> orderDetailList = order.getOrderDetailList();
        for(OrderDetail orderDetail : orderDetailList) {
            // 商品状态检查，其中有符合的条件，则跳出循环，设置status，结束addOrder函数的执行
            status = checkCommodityStatus(orderDetail.getCommodityId(), orderDetail.getCommodityNum());
            if(status == COMMODITY_IS_SOLD){
                appResponse = AppResponse.bizError("失败，有些商品未上架");
                break;
            }else if(status == COMMODITY_IS_LACK) {
                appResponse = AppResponse.bizError("失败，有些商品缺货中");
                break;
            }else if(status == COMMODITY_IS_LACK_INVENTORY){
                appResponse = AppResponse.bizError("失败，有些库存不足");
                break;
            }
            // 为每个订单明细设置订单id和订单明细id
            String orderDetailId = UUIDUtil.uuidStr();
            orderDetail.setOrderDetailId(orderDetailId).setOrderId(orderId);
        }

        // status状态判断
        if(status == SUCCESS) {
            // 订单明细添加
            int count = orderDetailDao.addOrderDetail(order.getOrderDetailList());
            // 商品状态修改
            count = count > 0 ? commodityDao.updateInventoryAndTotalCountByOrderDetail(order.getOrderDetailList()) : count;
            // 删除购物车中的商品
            count = count > 0 ? shopDao.deleteShoppingCartByOrderDetail(order.getOrderDetailList()) : count;
            if(count > 0) {
                // 订单添加
                count = orderDao.addOrder(order);
                if(count > 0) {
                    // 设置订单的总金额
                    order.setOrderPrice(orderDao.getOrderPriceById(orderId));
                    // 生成返回参数
                    Map<String, String> data = new HashMap<>(16);
                    data.put("orderNumber", order.getOrderNumber());
                    data.put("orderPrice", order.getOrderPrice());
                    appResponse = AppResponse.success("支付成功", data);
                }
            }
        }
        return appResponse;
    }

    /**
     * @Dept：第四组
     * @Description：收藏列表
     * @Author：林进华
     * @Date: 2019/4/20
     * @Param：collectVo 收藏信息
     * @Return：com.neusoft.common.response.AppResponse
     */
    @Override
    public AppResponse commodityCollectionList(Collect collect) {
        AppResponse appResponse;
        // 得到用户收藏集合
        List<CollectDetailVO> commodityCollectionList = collecDao.getCommodityCollectionList(collect);
        // 查询状态判断
        if(commodityCollectionList == null) {
            appResponse = AppResponse.bizError("查询失败");
        }else if(commodityCollectionList.isEmpty()) {
            appResponse = AppResponse.success("还没有收藏记录");
        }else {
            appResponse = AppResponse.success("查询成功", commodityCollectionList);
        }
        return appResponse;
    }
}
