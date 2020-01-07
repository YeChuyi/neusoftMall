package com.neusoft.CommodityUnit.service;

import com.neusoft.entity.commodityCenter.CommodityUnit;
import com.neusoft.entity.commodityCenter.CommodityUnitVO;
import com.neusoft.entity.common.response.AppResponse;

/**
 * @Author 715
 * @Date 2019/4/22 23:12
 */
public interface CommodityUnitService {
//商品单位查询
    AppResponse getCommodityUnitListPage(CommodityUnitVO commodityUnitVO);

//商品单位查询(无分页)
    AppResponse getCommodityUnitList(CommodityUnit CommodityUnit);

//商品单位新增
    AppResponse addCommodityUnit(CommodityUnit CommodityUnit);

//商品单位修改
    AppResponse updateCommodityUnit(CommodityUnit CommodityUnit);

//商品单位详情
    AppResponse getCommodityUnitDetail(CommodityUnit CommodityUnit);

//商品单位删除
    void deleteCommodityUnit(CommodityUnit CommodityUnit);



}
