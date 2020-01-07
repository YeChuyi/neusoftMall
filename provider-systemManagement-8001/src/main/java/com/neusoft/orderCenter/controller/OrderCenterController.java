package com.neusoft.orderCenter.controller;


import com.neusoft.config.RedisUtils;
import com.neusoft.entity.common.response.AppResponse;
import com.neusoft.entity.order.OrderVo;
import com.neusoft.orderCenter.service.OrderCenterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;


/**
 * @ClassName: OrderCenterController
 * @Description: 订单中心管理
 * @Author: 蔡程健
 * @Date: 2019/4/22
 */
@RestController
@RequestMapping("front/orderCenter")
@Slf4j
@Api("订单中心")
public class OrderCenterController {

    @Resource
    RedisUtils redisUtils;

    @Resource
    OrderCenterService orderCenterService;

    /**
     * @描述：查询客户自己的订单
     * @作成者：蔡程健
     * @作成时间: 2019/4/24
     */
    @ApiOperation(value = "获取订单列表")
    @GetMapping(value = "getOrderList")
    public AppResponse getOrders(@RequestParam(value = "orderNumber",required = false) String orderNumber,
                                 @RequestParam(value = "orderState",required = false) String orderState,
                                 @RequestParam(value = "orderDateStart",required = false) String orderDateStart,
                                 @RequestParam(value = "orderDateEnd",required = false) String orderDateEnd,
                                 @RequestParam(value = "customerId",required = true)String customerId,
                                 @RequestParam(value = "pageNum",required = true) int pageNum,
                                 @RequestParam(value = "pageSize",required = true) int pageSize) throws Exception {
        try {
            if (orderDateStart!=null && !orderDateStart.equals("") && !orderDateStart.equals("null")){
                orderDateStart =  orderDateStart.substring(0,orderDateStart.length()-13)+"+0800";
                orderDateStart = parseTime(orderDateStart);
            }
            if (orderDateEnd!=null && !orderDateEnd.equals("") && !orderDateEnd.equals("null")){
                orderDateEnd = orderDateEnd.substring(0,orderDateEnd.length()-13)+"+0800";
                orderDateEnd = parseTime(orderDateEnd);
            }
            return orderCenterService.getOrders(orderNumber,orderState,orderDateStart,orderDateEnd,customerId,pageNum,pageSize);
        }catch (Exception e) {
            log.error("订单获取异常", e);
            throw new Exception("查询错误，请重试");
        }
    }


    /**
     * @Description：查询客户自己的订单详情
     * @Author：蔡程健
     * @Date: 2019/4/24
     * @param orderId
     * @Return：com.neusoft.common.response.AppResponse
     */
    @ApiOperation(value = "获取订单详情")
    @GetMapping(value = "getOrderDetail")
    public AppResponse getOrderDetail(@RequestParam(value = "orderId") String orderId)throws Exception{
        try{
            return orderCenterService.getOrderDetail(orderId);
        }catch(Exception e) {
            log.error("订单获取异常", e);
            throw new Exception("查询错误，请重试");
        }
    }

    /**
     * @Description：修改客户自己的订单状态
     * @Author：蔡程健
     * @Date: 2019/4/24
     * @param orderVo
     * @Return：com.neusoft.common.response.AppResponse
     */
    @ApiOperation(value = "修改订单")
    @PutMapping(value = "updateOrderStatus")
    public AppResponse updateOrderStatus(OrderVo orderVo){
        orderVo.setLastModifiedBy("ccj");
        return orderCenterService.updateOrderStatus(orderVo);
    }

    /**
     * @Description：日期时间格式化
     * @Author：蔡程健
     * @Date: 2019/4/24
     * @param datdString
     * @Return：String
     */
    public static String parseTime(String datdString) {
        datdString = datdString.replace("GMT", "").replaceAll("\\(.*\\)", "");
        //将字符串转化为date类型，格式2016-10-12
        SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss z", Locale.ENGLISH);
        Date dateTrans = null;
        try {
            dateTrans = format.parse(datdString);
            return new SimpleDateFormat("yyyyMMddHHmmss").format(dateTrans);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return datdString;
    }
}
