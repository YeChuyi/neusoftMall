package com.neusoft.order.controller;

import com.neusoft.config.RedisUtils;
import com.neusoft.entity.common.response.AppResponse;
import com.neusoft.entity.order.OrderVo;
import com.neusoft.order.service.OrderService;
import com.neusoft.order.service.impl.OrderServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


/**
 * @ClassName: OrderController
 * @Description: 订单管理
 * @Author: 蔡程健
 * @Date: 2019/4/22
 */
@RestController
@RequestMapping("backend/order")
@Slf4j
@Api("订单管理")
public class OrderController {

    @Resource
    OrderService orderService;

    @Autowired
    private RedisUtils redisUtils;

    /**
     * @Description：获取订单
     * @Author：蔡程健
     * @Date: 2019/4/24
     * @param orderNumber
     * @param orderState
     * @param orderDateStart
     * @param orderDateEnd
     * @param pageNum
     * @param pageSize
     * @Return：com.neusoft.common.response.AppResponse
     * @throws Exception
     */
    @ApiOperation(value ="获取订单列表")
    @GetMapping(value = "getOrders")
    public AppResponse getOrders(@RequestParam(value = "orderNumber",required = false) String orderNumber,
                                 @RequestParam(value = "orderState",required = false) String orderState,
                                 @RequestParam(value = "orderDateStart",required = false) String  orderDateStart,
                                 @RequestParam(value = "orderDateEnd",required = false) String orderDateEnd,
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
            return orderService.getOrders(orderNumber,orderState,orderDateStart,orderDateEnd,pageNum,pageSize);
        }catch (Exception e) {
            log.error("订单获取异常", e);
            throw new Exception("查询错误，请重试");
        }
    }

    /**
     * @Description：获取订单详情
     * @Author：蔡程健
     * @Date: 2019/4/24
     * @param orderId
     * @Return：com.neusoft.common.response.AppResponse
     */
    @ApiOperation(value = "获取订单详情")
    @GetMapping(value = "getOrderDetail")
    public AppResponse getOrderDetail(@RequestParam(value = "orderId") String orderId)throws Exception{
        try{
            return orderService.getOrderDetail(orderId);
        }catch(Exception e) {
            log.error("订单获取异常", e);
            throw new Exception("查询错误，请重试");
        }
    }


    /**
     * @Description：修改订单
     * @Author：蔡程健
     * @Date: 2019/4/24
     * @param orderVo
     * @Return：com.neusoft.common.response.AppResponse
     */
    @ApiOperation(value = "修改订单")
    @PutMapping(value = "updateOrderStatus")
    public AppResponse updateOrderStatus(OrderVo orderVo){
        String userId = redisUtils.get(orderVo.getToken()+"_id");
        orderVo.setLastModifiedBy(userId);
        return orderService.updateOrderStatus(orderVo);
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
