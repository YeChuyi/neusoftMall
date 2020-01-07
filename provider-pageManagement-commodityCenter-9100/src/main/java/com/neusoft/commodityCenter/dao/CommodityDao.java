package com.neusoft.commodityCenter.dao;

import com.neusoft.entity.commodityCenter.*;
import com.neusoft.entity.commodityCenter.request.CenterBasePage;
import com.neusoft.entity.goods.Commodity;
import com.neusoft.entity.order.OrderDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommodityDao {

    /**
     * 功能：按条件查询全部商品
     * 作成者：林进华
     * 作成时间：2019/4/21
     */
    List<Commodity> getCategoryList(CenterBasePage centerBasePage);

    /**
     * 功能：根据商品id查询商品详情
     * 作成者：林进华
     * 作成时间：2019/4/21
     */
    CommodityCenterDetail getCommodityCenterDetail(CommodityCenterDetail commodityCenterDetail);

    /**
     * 功能：根据一级分类查询同类商品
     * 作成者：林进华
     * 作成时间：2019/4/21
     */
    List<Commodity> getCommodityCenterSimilar(Commodity commodity);

    /**
     * 功能：根据一级分类查询同类商品
     * 作成者：林进华
     * 作成时间：2019/4/21
     */
    List<Commodity> getCommodityByIdList(@Param("commodityIdList") List<String> commodityIdList);

    /**
     * 功能：根据商品id查询商品的交易记录
     * 作成者：林进华
     * 作成时间：2019/4/21
     */
    List<CommodityCenterTradingVO> getCommodityCenterTrading(Commodity commodity);

    /**
     * 功能：生成商品的立即购买数据
     * 作成者：林进华
     * 作成时间：2019/4/21
     */
    CommodityBuyNowVO getCommodityBuyNow(CommodityBuyNow commodityBuyNow);

    /**
     * 功能：通过订单明细修改商品的库存和销售量
     * 作成者：林进华
     * 作成时间：2019/4/21
     */
    int updateInventoryAndTotalCountByOrderDetail(@Param("orderDetailList") List<OrderDetail> orderDetailList);
}
