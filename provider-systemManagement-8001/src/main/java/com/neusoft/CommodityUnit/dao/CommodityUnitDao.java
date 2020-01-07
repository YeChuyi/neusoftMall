package com.neusoft.CommodityUnit.dao;

import com.neusoft.entity.commodityCenter.CommodityUnit;
import com.neusoft.entity.commodityCenter.CommodityUnitVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author 715
 * @Date 2019/4/22 23:24
 */
@Mapper
public interface CommodityUnitDao {
    /**
     * 查询
     * 商品单位（分页）
     */
    List<CommodityUnitVO> getCommodityUnitListPage(CommodityUnitVO commodityUnitVO);

    /**
     * 查询
     * 商品单位
     */
    List<CommodityUnit> getCommodityUnitList(CommodityUnit commodityUnit);
    /**
     * 新增
     * 商品单位
     */
    int addCommodityUnit(CommodityUnit CommodityUnit);

    /**
     * 修改
     * 商品单位
     */
    int updateCommodityUnit(CommodityUnit CommodityUnit);

    /**
     * 获取
     * 商品单位详情
     */
    int getCommodityUnitDetail(CommodityUnit CommodityUnit);

    /**
     * 删除
     * 商品单位
     */
    int deleteCommodityUnit(CommodityUnit CommodityUnit);


}
