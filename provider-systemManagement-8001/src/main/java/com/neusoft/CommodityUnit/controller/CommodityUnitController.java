package com.neusoft.CommodityUnit.controller;

import com.neusoft.CommodityUnit.service.CommodityUnitService;
import com.neusoft.config.RedisUtils;
import com.neusoft.entity.commodityCenter.CommodityUnit;
import com.neusoft.entity.commodityCenter.CommodityUnitVO;
import com.neusoft.entity.common.response.AppResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @Author 715
 * @Date 2019/4/22 23:24
 */
@RestController
@RequestMapping("backend/CommodityUnit")
@Slf4j
@Api("商品单位")
public class CommodityUnitController {

    @Autowired
    private RedisUtils redisUtils;

    @Resource
    private CommodityUnitService commodityUnitService;

    @ApiOperation(value ="获取商品单位")
    @GetMapping(value = "getCommodityUnitListPage")
    public AppResponse getCommodityUnitListPage(CommodityUnitVO CommodityUnitVO) throws Exception {
        try {
            return AppResponse.success("查询成功", commodityUnitService.getCommodityUnitListPage(CommodityUnitVO));
        } catch (Exception e) {
            log.error("获取商品单位获取异常", e);
            throw new Exception("查询错误，请重试");
        }
    }


    @ApiOperation(value ="新增商品单位")
    @RequestMapping(value = "addCommodityUnit",method = RequestMethod.POST)
    public AppResponse addCommodityUnit(@Valid CommodityUnitVO CommodityUnitVO) {
        try {
            //获取用户id
            String userId = redisUtils.get(CommodityUnitVO.getToken()+"_id");
            CommodityUnitVO.setLastModifiedBy(userId);
            CommodityUnitVO.setCreatedBy(userId);
            CommodityUnit commodityUnit = new CommodityUnit();
            BeanUtils.copyProperties(CommodityUnitVO, commodityUnit);
            return commodityUnitService.addCommodityUnit(commodityUnit);
        } catch (Exception e) {
            log.error("新增商品单位异常", e);
            return AppResponse.notFound("新增失败");
        }
    }

    @ApiOperation(value ="商品单位修改")
    @RequestMapping(value = "updateCommodityUnit",method = RequestMethod.PUT, produces = {"application/json;charset=UTF-8"})
    public AppResponse updateCommodityUnit(@Valid CommodityUnitVO CommodityUnitVO) throws Exception{
        try {
            String userId = redisUtils.get(CommodityUnitVO.getToken()+"_id");
            CommodityUnitVO.setLastModifiedBy(userId);
            CommodityUnitVO.setCreatedBy(userId);
            CommodityUnit commodityUnit =new CommodityUnit();
            BeanUtils.copyProperties(CommodityUnitVO, commodityUnit);
            return commodityUnitService.updateCommodityUnit(commodityUnit);
        } catch (Exception e) {
            log.error("商品单位信息修改错误", e);
            throw new Exception("系统错误，请重试");
        }
    }

    @ApiOperation(value ="商品单位删除")
    @RequestMapping(value = "deleteCommodityUnit",method = RequestMethod.PUT)
    public AppResponse deleteCommodityUnit(@Valid CommodityUnitVO CommodityUnitVO) {
        try {
            //使用redis的如下：
            //获取用户id
//            String userId = redisUtils.get(menuVO.getToken()+"_id");
//            menuVO.setLastModifiedBy(userId);
//            menuVO.setCreateBy(userId);
            CommodityUnitVO.setLastModifiedBy("bhl");
//            CommodityUnitVO.setCreateBy("bhl");
            CommodityUnit commodityUnit = new CommodityUnit();
            BeanUtils.copyProperties(CommodityUnitVO, commodityUnit);
            commodityUnitService.deleteCommodityUnit(commodityUnit);
            return AppResponse.success("商品单位删除成功");
        } catch (Exception e) {
            log.error("商品单位删除错误", e);
            return AppResponse.notFound("删除失败");
        }
    }

}
