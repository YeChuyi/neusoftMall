package com.neusoft.receive.controller;

import com.neusoft.config.RedisUtils;
import com.neusoft.entity.common.response.AppResponse;


import com.neusoft.entity.receive.Receive;
import com.neusoft.entity.receive.ReceiveVO;
import com.neusoft.entity.receive.Region;
import com.neusoft.entity.receive.RegionVO;
import com.neusoft.menu.controller.MenuController;
import com.neusoft.receive.service.ReceiveService;
import com.neusoft.util.UUIDUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @ClassName  ReceiveController
 * @Description  收获地址管理
 * @Author  叶楚义
 * @Date  2019/4/20
 */
@RestController
@RequestMapping("mall/front/receive")
@Validated
@Api("收获地址管理")
public class ReceiveController {

    private static final Logger log = LoggerFactory.getLogger(MenuController.class);

    @Resource
    private ReceiveService receiveService;  //注入receiveService业务逻辑层

    @Autowired
    private RedisUtils redisUtils;  //注入redis工具类

    /**
     * @Description 根据客户ID查询收获地址信息
     * @Author 叶楚义
     * @Date 2019/4/20
     * @param customerId 客户ID
     * @return com.neusoft.common.response.AppResponse
     */
    @ApiOperation(value ="收货地址列表")
    @RequestMapping(value = "getReceiveList/{customerId}" ,method = RequestMethod.GET)
    public AppResponse getGoodsDetail(@PathVariable("customerId") String customerId) throws Exception {
        try {
            return receiveService.getReceiveList(customerId);
        } catch (Exception e) {
            log.error("用户收获地址获取异常", e);
            throw new Exception("查询错误，请重试");
        }
    }


    /**
     * @Description 根据顾客ID来对其新增收货地址
     * @Author 叶楚义
     * @Date 2019/4/20
     * @param receiveVO   收货地址信息
     * @return com.neusoft.common.response.AppResponse
     */
    @ApiOperation(value ="收货地址新增")
    @RequestMapping(value = "addReceive/{customerId}",method = RequestMethod.POST)
    public AppResponse addReceive(@Valid ReceiveVO receiveVO, @PathVariable("customerId") String customerId) {
        try {
            //调用redis获取前台客户登录用户信息
            String userId = redisUtils.get(receiveVO.getToken()+"_id");
            receiveVO.setLastModifiedBy(userId);
            receiveVO.setCreatedBy(userId);
            Receive receive = new Receive();
            BeanUtils.copyProperties(receiveVO, receive);  //拷贝视图对象于持久化对象
            receive.setCreatedBy(customerId);  //将创建者ID赋值
            receive.setReceiveId(UUIDUtil.uuidStr());  //设计自增ID
            return receiveService.addReceive(receive);
        } catch (Exception e) {
            log.error("收获地址新增异常", e);
            return AppResponse.notFound("新增失败");
        }
    }

    /**
     * @Description 收货地址修改
     * @Author 叶楚义
     * @Date 2019/4/20
     * @param receiveVO  收货地址信息
     * @return com.neusoft.common.response.AppResponse
     */
    @ApiOperation(value ="收货地址修改")
    @RequestMapping(value = "updateReceive",method = RequestMethod.PUT)
    public AppResponse updateReceive(@Valid ReceiveVO receiveVO) {
        try {

            //调用redis获取前台客户登录用户信息
            String userId = redisUtils.get(receiveVO.getToken()+"_id");
            receiveVO.setLastModifiedBy(userId);
            Receive receive = new Receive();
            BeanUtils.copyProperties(receiveVO, receive); //拷贝视图对象于持久化对象
            return receiveService.updateReceive(receive);
        } catch (Exception e) {
            log.error("收货地址修改异常", e);
            return AppResponse.notFound("修改失败");
        }
    }

    /**
     * @Description 删除收货地址
     * @Author 叶楚义
     * @Date 2019/4/20
     * @param receiveVO 收货地址信息
     * @return com.neusoft.common.response.AppResponse
     */
    @ApiOperation(value ="删除收货地址")
    @RequestMapping(value = "deleteReceive",method = RequestMethod.PUT)
    public AppResponse deleteReceive(@Valid ReceiveVO receiveVO) {
        try {
            //调用redis获取前台客户登录用户信息
            String userId = redisUtils.get(receiveVO.getToken()+"_id");
            receiveVO.setLastModifiedBy(userId);
            receiveVO.setCreatedBy(userId);
            Receive receive = new Receive();
            BeanUtils.copyProperties(receiveVO, receive);  //拷贝视图对象于持久化对象
            receiveService.deleteReceive(receive);
            return AppResponse.success("删除收货地址成功");
        } catch (Exception e) {
            log.error("删除收货地址错误", e);
            return AppResponse.notFound("删除失败");
        }
    }
    /**
     * @Description 设为默认地址
     * @Author 叶楚义
     * @Date 2019/4/20
     * @param reveiceVO 收货地址信息
     * @return com.neusoft.common.response.AppResponse
     */
    @ApiOperation(value ="设为默认地址")
    @RequestMapping(value = "updateReceiveByDefault",method = RequestMethod.PUT)
    public AppResponse updateReceiveByDefault(@Valid ReceiveVO reveiceVO) throws Exception {
        try {

            //调用redis获取前台客户登录用户信息
            String userId = redisUtils.get(reveiceVO.getToken()+"_id");
            reveiceVO.setLastModifiedBy(userId);
            Receive receive =new Receive();
            BeanUtils.copyProperties(reveiceVO, receive);  //拷贝视图对象于持久化对象
            return receiveService.updateReceiveByDefault(receive);
        } catch (Exception e) {
            log.error("设为默认地址信息错误", e);
            throw new Exception("系统错误，请重试");
        }
    }

    /**
     * @Description 查询省市区
     * @Author 叶楚义
     * @Date 2019/4/20
     * @param regionVO 地区信息
     * @return com.neusoft.common.response.AppResponse
     */
    @ApiOperation("查询省市区")
    @RequestMapping(value="getRegion",method =RequestMethod.GET)
    public AppResponse getRegion(@Valid RegionVO regionVO) throws Exception {
        try {
            Region region =new Region();
            BeanUtils.copyProperties(regionVO, region);   //拷贝视图对象于持久化对象
            return receiveService.getRegion(region);
        } catch (Exception e) {
            log.error("查询省市区获取异常", e);
            throw new Exception("查询错误，请重试");
        }

    }





}
