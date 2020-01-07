package com.neusoft.commodityCenter.dao;

import com.neusoft.entity.collect.Collect;
import com.neusoft.entity.collect.CollectDetailVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CollecDao {

    /**
     * 功能：按条件查询全部商品
     * 作成者：林进华
     * 作成时间：2019/4/21
     */
    int addCommodityCollection(@Param("collectList") List<Collect> collectList);

    /**
     * 功能：取消商品收藏
     * 作成者：林进华
     * 作成时间：2019/4/21
     */
    int deleteCommodityCollection(@Param("collectList") List<Collect> collectList);

    /**
     * 功能：判断该商品是否已收藏
     * 作成者：林进华
     * 作成时间：2019/4/21
     */
    int getIsCollect(Collect collect);

    /**
     * 功能：得到用户的收藏列表
     * 作成者：林进华
     * 作成时间：2019/4/21
     */
    List<CollectDetailVO> getCommodityCollectionList(Collect collect);
}
