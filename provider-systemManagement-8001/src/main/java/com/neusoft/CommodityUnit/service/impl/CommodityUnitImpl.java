package com.neusoft.CommodityUnit.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.CommodityUnit.dao.CommodityUnitDao;
import com.neusoft.CommodityUnit.service.CommodityUnitService;
import com.neusoft.config.RedisUtils;
import com.neusoft.entity.commodityCenter.CommodityUnit;
import com.neusoft.entity.commodityCenter.CommodityUnitVO;
import com.neusoft.entity.common.Base.PageVo;
import com.neusoft.entity.common.response.AppResponse;
import com.neusoft.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author 715
 * @Date 2019/4/22 23:23
 */
@Transactional
@Service
public class CommodityUnitImpl implements CommodityUnitService {

    @Resource
    private CommodityUnitDao commodityUnitDao;

    @Autowired
    private RedisUtils redisUtils;

//    @Value("${rootId}")
//    private String rootId;

    /**
     * 查询
     * 商品单位（分页）
     */
    @Override
    public AppResponse getCommodityUnitListPage(CommodityUnitVO commodityUnitVO) {
        //查询商品单位
        PageHelper.startPage(commodityUnitVO.getPageNum(), commodityUnitVO.getPageSize());
        List<CommodityUnitVO> commodityUnitListVo =commodityUnitDao.getCommodityUnitListPage(commodityUnitVO);
        PageVo pageVo = new PageVo();
        pageVo.setList(commodityUnitListVo);
        pageVo.setTotalRecords((int) new PageInfo<CommodityUnitVO>(commodityUnitListVo).getTotal());
        return AppResponse.success("查询成功！", pageVo);
    }

    /**
     * 查询
     * 商品单位
     */
    @Override
    public AppResponse getCommodityUnitList(CommodityUnit CommodityUnit) {
        return null;
    }

    /**
     * 新增
     * 商品单位
     */
    @Override
    public AppResponse addCommodityUnit(CommodityUnit CommodityUnit) {
        AppResponse appResponse = AppResponse.success("新增成功！");
        // 新增菜单
        CommodityUnit.setUnitId(UUIDUtil.uuidStr());
        int count = commodityUnitDao.addCommodityUnit(CommodityUnit);
        if(0 == count) {
            appResponse = AppResponse.bizError("新增失败，请重试！");
        }
        return appResponse;
    }

    /**
     * 修改
     * 商品单位
     */
    @Override
    public AppResponse updateCommodityUnit(CommodityUnit CommodityUnit) {
        AppResponse appResponse = AppResponse.success("修改成功！");
        int count = commodityUnitDao.updateCommodityUnit(CommodityUnit);
        if(0 == count) {
            appResponse = AppResponse.bizError("修改失败，请重试！");
        }
        return appResponse;
    }

    /**
     * 获取
     * 商品单位详情
     */
    @Override
    public AppResponse getCommodityUnitDetail(CommodityUnit CommodityUnit) {
        return null;
    }

    /**
     * 删除
     * 商品单位
     */
    @Override
    public void deleteCommodityUnit(CommodityUnit CommodityUnit) {
        // 删除商品单位/
        commodityUnitDao.deleteCommodityUnit(CommodityUnit);
    }
}
