package com.neusoft.goods.dao;
import java.util.List;
import com.neusoft.entity.goods.Commodity;
import com.neusoft.entity.goods.CommodityPic;
import com.neusoft.entity.goods.CommodityVO;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
@Mapper
public interface GoodsDao {

    /**
     * @Description 商品列表
     * @Author 叶楚义
     * @Date 2019/4/19
     * @param commodityVO 商品信息
     * @return com.neusoft.common.response.AppResponse
     */
    List<Commodity> listGoods(CommodityVO commodityVO);



    /**
     * @Description 商品上架/下架
     * @Author 叶楚义
     * @Date 2019/4/20
     * @param commodity 商品信息
     * @return com.neusoft.common.response.AppResponse
     */
    int updateGoodsIsSell(Commodity commodity);


    /**
     * @Description 根据commodityId查询商品信息
     * @Author 叶楚义
     * @Date 2019/4/20
     * @param commodityVO 商品信息
     * @return com.neusoft.common.response.AppResponse
     */
    Commodity getGoodsDetail(CommodityVO commodityVO);



    /**
     * @Description 商品删除
     * @Author 叶楚义
     * @Date 2019/4/20
     * @param commodity 商品信息
     * @return com.neusoft.common.response.AppResponse
     */
    int deleteGoods(Commodity commodity);

    /**
     * 功能：商品修改
     * 作成者：cqluo
     * 作成时间：2019/4/23
     */
    int updateGoods(Commodity commodity);

    /**
     * 功能：商品新增
     * 作成者：cqluo
     * 作成时间：2019/4/23
     */
    int addGoods(Commodity commodity);

    /**
     * 功能：添加图片
     * 作成者：cqluo
     * 作成时间：2019/4/23
     */
    int addPic(CommodityPic commodityPic);

    /**
     * 功能：修改图片
     * 作成者：cqluo
     * 作成时间：2019/4/23
     */
    int updatePic(CommodityPic commodityPic);
}
