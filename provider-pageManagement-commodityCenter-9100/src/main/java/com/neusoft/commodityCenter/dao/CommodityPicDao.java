package com.neusoft.commodityCenter.dao;
import com.neusoft.entity.goods.Commodity;
import com.neusoft.entity.goods.CommodityPicVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommodityPicDao {

    /**
     * 功能：得到商品的图片集合
     * 作成者：林进华
     * 作成时间：2019/4/21
     */
    List<CommodityPicVo> getPictureList(Commodity commodity);

}
