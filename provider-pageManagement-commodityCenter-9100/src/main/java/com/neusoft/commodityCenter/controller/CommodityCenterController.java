package com.neusoft.commodityCenter.controller;

import com.neusoft.commodityCenter.service.CommodityCenterService;
import com.neusoft.config.RedisUtils;
import com.neusoft.entity.collect.Collect;
import com.neusoft.entity.collect.CollectVo;
import com.neusoft.entity.commodityCenter.*;
import com.neusoft.entity.commodityCenter.request.CenterBasePage;
import com.neusoft.entity.commodityCenter.request.CenterBasePageVo;
import com.neusoft.entity.common.response.AppResponse;
import com.neusoft.entity.goods.Commodity;
import com.neusoft.entity.goods.CommodityVO;
import com.neusoft.entity.order.Order;
import com.neusoft.entity.order.OrderDetail;
import com.neusoft.entity.order.OrderDetailVO;
import com.neusoft.entity.order.OrderVo;
import com.neusoft.entity.shop.Shop;
import com.neusoft.entity.shop.ShopVo;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("mall/front/CommodityCenter")
public class CommodityCenterController {

    private static final Logger logger = LoggerFactory.getLogger(CommodityCenterController.class);

    @Resource
    private CommodityCenterService commodityCenterService;

    @Autowired
    private RedisUtils redisUtils;

    /**
     * @Dept：第四组
     * @Description：商品中心商品查询
     * @Author：林进华
     * @Date: 2019/4/20
     * @Param：centerBasePageVo 商品中心分页信息
     * @Return：com.neusoft.common.response.AppResponse
     */
    @ApiOperation(value ="商品中心商品集合")
    @RequestMapping(value = "/getCommodityList", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    public AppResponse getCommodityList(CenterBasePageVo centerBasePageVo) {
        try {
            CenterBasePage centerBasePage = new CenterBasePage();
            BeanUtils.copyProperties(centerBasePageVo, centerBasePage);
            AppResponse appResponse = commodityCenterService.getCommodityList(centerBasePage);
            return appResponse;
        } catch (Exception e) {
            logger.error("商品中心商品集合查询错误", e);
            return AppResponse.notFound("查询失败");
        }
    }

    /**
     * @Dept：第四组
     * @Description：添加商品到购物车
     * @Author：林进华
     * @Date: 2019/4/20
     * @Param：shopVo 购物车信息
     * @Return：com.neusoft.common.response.AppResponse
     */
    @ApiOperation(value ="添加商品到购物车")
    @RequestMapping(value = "/addShoppingCart", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public AppResponse addShoppingCart(ShopVo shopVo) {
        try {
            //获取用户id
            String userId = redisUtils.get(shopVo.getToken()+"_id");
            // 从redis获取用户id
            shopVo.setCustomerId(userId);
            shopVo.setLastModifiedBy(userId);
            shopVo.setCreatedBy(userId);
            Shop shop = new Shop();
            BeanUtils.copyProperties(shopVo, shop);
            AppResponse appResponse = commodityCenterService.addShoppingCart(shop);
            return appResponse;
        } catch (Exception e) {
            logger.error("添加商品到购物车错误", e);
            return AppResponse.notFound("添加失败");
        }
    }

    /**
     * @Dept：第四组
     * @Description：得到商品详情
     * @Author：林进华
     * @Date: 2019/4/20
     * @Param：commodityCenterDetailVO 商品中心详情信息
     * @Return：com.neusoft.common.response.AppResponse
     */
    @ApiOperation(value ="得到商品详情")
    @RequestMapping(value = "/getCommodityCenterDeatil", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    public AppResponse getCommodityCenterDeatil(CommodityCenterDetailVO commodityCenterDetailVO) {
        try {
            CommodityCenterDetail commodityCenterDetail = new CommodityCenterDetail();
            BeanUtils.copyProperties(commodityCenterDetailVO, commodityCenterDetail);
            AppResponse appResponse = commodityCenterService.getCommodityCenterDetail(commodityCenterDetail);
            return appResponse;
        } catch (Exception e) {
            logger.error("商品详情查询错误", e);
            return AppResponse.notFound("商品详情查询失败");
        }
    }

    /**
     * @Dept：第四组
     * @Description：同类商品推荐集合
     * @Author：林进华
     * @Date: 2019/4/20
     * @Param：commodityVO 商品信息
     * @Return：com.neusoft.common.response.AppResponse
     */
    @ApiOperation(value ="同类商品推荐集合")
    @RequestMapping(value = "/getCommodityCenterSimilar", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    public AppResponse getCommodityCenterSimilar(CommodityVO commodityVO) {
        try {
            Commodity commodity = new Commodity();
            BeanUtils.copyProperties(commodityVO, commodity);
            AppResponse appResponse = commodityCenterService.getCommodityCenterSimilar(commodity);
            return appResponse;
        } catch (Exception e) {
            logger.error("同类商品集合查询错误", e);
            return AppResponse.notFound("同类商品集合查询失败");
        }
    }

    /**
     * @Dept：第四组
     * @Description：交易记录
     * @Author：林进华
     * @Date: 2019/4/20
     * @Param：commodityVO 商品信息
     * @Return：com.neusoft.common.response.AppResponse
     */
    @ApiOperation(value ="交易记录")
    @RequestMapping(value = "/getCommodityCenterTrading", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    public AppResponse getCommodityCenterTrading(CommodityVO commodityVO) {
        try {
            Commodity commodity = new Commodity();
            BeanUtils.copyProperties(commodityVO, commodity);
            AppResponse appResponse = commodityCenterService.getCommodityCenterTradin(commodity);
            return appResponse;
        } catch (Exception e) {
            logger.error("交易记录查询错误", e);
            return AppResponse.notFound("交易记录查询失败");
        }
    }

    /**
     * @Dept：第四组
     * @Description：立即购买
     * @Author：林进华
     * @Date: 2019/4/20
     * @Param：commodityBuyNowVO 立即购买信息
     * @Return：com.neusoft.common.response.AppResponse
     */
    @ApiOperation(value ="立即购买")
    @RequestMapping(value = "/getCommodityBuyNow", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public AppResponse getCommodityBuyNow(CommodityBuyNowVO commodityBuyNowVO) {
        try {
            CommodityBuyNow commodityBuyNow = new CommodityBuyNow();
            BeanUtils.copyProperties(commodityBuyNowVO, commodityBuyNow);
            AppResponse appResponse = commodityCenterService.getCommodityBuyNow(commodityBuyNow);
            return appResponse;
        } catch (Exception e) {
            logger.error("立即购买错误", e);
            return AppResponse.notFound("立即购买失败");
        }
    }

    /**
     * @Dept：第四组
     * @Description：添加收藏/取消收藏
     * @Author：林进华
     * @Date: 2019/4/20
     * @Param：collectVoList 收藏列表集合信息
     * @Return：com.neusoft.common.response.AppResponse
     */
    @ApiOperation(value ="添加收藏/取消收藏")
    @RequestMapping(value = "/commodityCollection", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public AppResponse commodityCollection(@RequestBody List<CollectVo> collectVoList) {
        try {
            // 判断是否有操作的对象
            if(collectVoList == null || collectVoList.isEmpty()) {
                return AppResponse.notFound("没有操作的对象");
            }
            // 获取用户id
            String userId = redisUtils.get(collectVoList.get(0).getToken() + "_id");
            List<Collect> collectList = new ArrayList<>();
            for(CollectVo collectVo : collectVoList) {
                //  为每一个收藏对象添加用户id
                collectVo.setCustomerId(userId);
                collectVo.setLastModifiedBy(userId);
                collectVo.setCreatedBy(userId);
                Collect collect = new Collect();
                BeanUtils.copyProperties(collectVo, collect);
                collectList.add(collect);
            }
            AppResponse appResponse = commodityCenterService.commodityCollection(collectList);
            return appResponse;
        } catch (Exception e) {
            logger.error("添加收藏/取消收藏错误", e);
            return AppResponse.notFound("添加收藏/取消收藏失败");
        }
    }

    /**
     * @Dept：第四组
     * @Description：提交订单
     * @Author：林进华
     * @Date: 2019/4/20
     * @Param：orderVo 订单信息
     * @Return：com.neusoft.common.response.AppResponse
     */
    @ApiOperation(value ="提交订单")
    @RequestMapping(value = "/addOrder", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public AppResponse addOrder(OrderVo orderVo) {
        try {
            // 获取用户id
            String userId = redisUtils.get(orderVo.getToken() + "_id");
            orderVo.setCustomerId(userId);
            orderVo.setLastModifiedBy(userId);
            orderVo.setCreatedBy(userId);
            Order order = new Order();
            BeanUtils.copyProperties(orderVo, order);
            // BeanUtils拷贝List<OrderDetail> 拷贝不了，所以需要自己设置list，再逐个拷贝orderVo中list的对象的值
            List<OrderDetail> orderDetailList = new ArrayList<>();
            order.setOrderDetailList(orderDetailList);
            for(OrderDetailVO temp : orderVo.getCommodityList()) {
                // 新建一个订单明细对象，作为Vo对象值copy的容器
                OrderDetail orderDetail = new OrderDetail();
                temp.setLastModifiedBy(userId);
                temp.setCreatedBy(userId);
                // 对象值拷贝
                BeanUtils.copyProperties(temp, orderDetail);
                // 订单明细对象添加
                orderDetailList.add(orderDetail);
            }
            AppResponse appResponse = commodityCenterService.addOrder(order);
            return appResponse;
        } catch (Exception e) {
            logger.error("提交订单错误", e);
            return AppResponse.notFound("提交订单失败");
        }
    }

    /**
     * @Dept：第四组
     * @Description：收藏列表
     * @Author：林进华
     * @Date: 2019/4/20
     * @Param：collectVo 收藏信息
     * @Return：com.neusoft.common.response.AppResponse
     */
    @ApiOperation(value ="收藏列表")
    @RequestMapping(value = "/commodityCollectionList", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    public AppResponse commodityCollectionList(CollectVo collectVo) {
        try {
            // 获取用户id
            String userId = redisUtils.get(collectVo.getToken() + "_id");
            collectVo.setCustomerId(userId);
            Collect collect = new Collect();
            BeanUtils.copyProperties(collectVo, collect);
            AppResponse appResponse = commodityCenterService.commodityCollectionList(collect);
            return appResponse;
        } catch (Exception e) {
            logger.error("收藏列表查询错误", e);
            return AppResponse.notFound("收藏列表查询失败");
        }
    }

}
