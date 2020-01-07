package com.neusoft.goods.service;
import com.neusoft.entity.goods.Commodity;
import com.neusoft.entity.goods.CommodityVO;
import com.neusoft.entity.common.response.AppResponse;

/**
 * @ClassName GoodsService
 * @Description 商品管理接口类
 * @Author 叶楚义
 * @Date 2019/4/18
 */
public interface GoodsService {

    /**
     * @Description 商品列表
     * @Author 叶楚义
     * @Date 2019/4/19
     * @param commodityVO   商品信息
     * @return com.neusoft.common.response.AppResponse
     */
    AppResponse listGoods(CommodityVO commodityVO);

    /**
     * @Description 商品上架/下架
     * @Author 叶楚义
     * @Date 2019/4/20
     * @param commodity   商品信息
     * @return com.neusoft.common.response.AppResponse
     */

    AppResponse updateGoodsIsSell(Commodity commodity);

    /**
     * @Description 根据commodityId查询商品信息
     * @Author 叶楚义
     * @Date 2019/4/20
     * @param commodityVO 商品信息
     * @return com.neusoft.entity.goods.Commodity;
     */
    Commodity getGoodsDetail(CommodityVO commodityVO);

    /**
     * @Description 商品删除
     * @Author 叶楚义
     * @Date 2019/4/20
     * @param commodity 商品信息
     */
    void deleteGoods(Commodity commodity);

    /**
     * 功能：商品修改
     * 作成者：cqluo
     * 作成时间：2019/4/23
     */
    AppResponse updateGoods(Commodity commodity);

    /**
     * 功能：商品新增
     * 作成者：cqluo
     * 作成时间：2019/4/23
     */
    AppResponse addGoods(Commodity commodity);

}
