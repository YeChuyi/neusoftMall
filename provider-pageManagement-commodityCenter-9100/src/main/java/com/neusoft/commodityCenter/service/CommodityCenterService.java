package com.neusoft.commodityCenter.service;

import com.neusoft.entity.commodityCenter.CommodityBuyNow;
import com.neusoft.entity.collect.Collect;
import com.neusoft.entity.commodityCenter.CommodityCenterDetail;
import com.neusoft.entity.commodityCenter.request.CenterBasePage;
import com.neusoft.entity.common.response.AppResponse;
import com.neusoft.entity.goods.Commodity;
import com.neusoft.entity.order.Order;
import com.neusoft.entity.shop.Shop;

import java.util.List;

public interface CommodityCenterService {

    /**
     * @Dept：第四组
     * @Description：商品中心商品查询
     * @Author：林进华
     * @Date: 2019/4/20
     * @Param：centerBasePageVo 商品中心分页信息
     * @Return：com.neusoft.common.response.AppResponse
     */
    AppResponse getCommodityList(CenterBasePage centerBasePage);

    /**
     * @Dept：第四组
     * @Description：添加商品到购物车
     * @Author：林进华
     * @Date: 2019/4/20
     * @Param：shopVo 购物车信息
     * @Return：com.neusoft.common.response.AppResponse
     */
    AppResponse addShoppingCart(Shop shop);

    /**
     * @Dept：第四组
     * @Description：得到商品详情
     * @Author：林进华
     * @Date: 2019/4/20
     * @Param：commodityCenterDetailVO 商品中心详情信息
     * @Return：com.neusoft.common.response.AppResponse
     */
    AppResponse getCommodityCenterDetail(CommodityCenterDetail args);

    /**
     * @Dept：第四组
     * @Description：同类商品推荐集合
     * @Author：林进华
     * @Date: 2019/4/20
     * @Param：commodityVO 商品信息
     * @Return：com.neusoft.common.response.AppResponse
     */
    AppResponse getCommodityCenterSimilar(Commodity commodity);

    /**
     * @Dept：第四组
     * @Description：交易记录
     * @Author：林进华
     * @Date: 2019/4/20
     * @Param：commodityVO 商品信息
     * @Return：com.neusoft.common.response.AppResponse
     */
    AppResponse getCommodityCenterTradin(Commodity commodity);

    /**
     * @Dept：第四组
     * @Description：立即购买
     * @Author：林进华
     * @Date: 2019/4/20
     * @Param：commodityBuyNowVO 立即购买信息
     * @Return：com.neusoft.common.response.AppResponse
     */
    AppResponse getCommodityBuyNow(CommodityBuyNow commodityBuyNow);

    /**
     * @Dept：第四组
     * @Description：添加收藏/取消收藏
     * @Author：林进华
     * @Date: 2019/4/20
     * @Param：collectVoList 收藏列表集合信息
     * @Return：com.neusoft.common.response.AppResponse
     */
    AppResponse commodityCollection(List<Collect> collectList);

    /**
     * @Dept：第四组
     * @Description：提交订单
     * @Author：林进华
     * @Date: 2019/4/20
     * @Param：orderVo 订单信息
     * @Return：com.neusoft.common.response.AppResponse
     */
    AppResponse addOrder(Order order);

    /**
     * @Dept：第四组
     * @Description：收藏列表
     * @Author：林进华
     * @Date: 2019/4/20
     * @Param：collectVo 收藏信息
     * @Return：com.neusoft.common.response.AppResponse
     */
    AppResponse commodityCollectionList(Collect collect);

}
