package com.neusoft.orderCenter.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.config.RedisUtils;
import com.neusoft.entity.common.Base.PageVo;
import com.neusoft.entity.common.response.AppResponse;
import com.neusoft.entity.order.Order;
import com.neusoft.entity.order.OrderDetailVO;
import com.neusoft.entity.order.OrderVo;
import com.neusoft.orderCenter.dao.OrderCenterDao;
import com.neusoft.orderCenter.service.OrderCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;


/**
 * @ClassName: OrderCenterServiceImpl
 * @Description: 订单中心
 * @Author: 蔡程健
 * @Date: 2019/4/24
 */
@Transactional
@Service
public class OrderCenterServiceImpl implements OrderCenterService {

    @Resource
    OrderCenterDao orderCenterDao;

    @Autowired
    private RedisUtils redisUtils;

    /**
     * @Description：查询客户自己的订单
     * @Author：蔡程健
     * @Date: 2019/4/24
     * @param orderNumber
     * @param orderState
     * @param orderDateStart
     * @param orderDateEnd
     * @param customerId
     * @param pageNum
     * @param pageSize
     * @return com.neusoft.common.response.AppResponse
     */
    @Override
    public AppResponse getOrders(String orderNumber, String orderState, String orderDateStart, String orderDateEnd, String customerId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Order> orderList = orderCenterDao.getOrderList(orderNumber,orderState,orderDateStart,orderDateEnd,customerId);
        PageVo pageVo = new PageVo();
        pageVo.setList(orderList);
        if(orderList==null || orderList.size()==0){
            return AppResponse.notFound("没有该条记录");
        }else {
            pageVo.setTotalRecords((int) new PageInfo<Order>(orderList).getTotal());
        }
        return AppResponse.success("查询成功",pageVo);
    }


    /**
     * @Description：查询客户自己的订单详情
     * @Author：蔡程健
     * @Date: 2019/4/24
     * @param orderId
     * @return com.neusoft.common.response.AppResponse
     */
    @Override
    public AppResponse getOrderDetail(String orderId) {
        OrderVo orderVo = orderCenterDao.getOrderVo(orderId);
        if (orderVo==null || orderVo.equals(null) || orderVo.equals("")){
            return AppResponse.notFound("没有该条记录");
        }else{
            List<OrderDetailVO> orderDetailList = orderCenterDao.getOrderDetail(orderId);
            orderVo.setCommodityList(orderDetailList);
            return AppResponse.success("查询成功",orderVo);
        }
    }

    @Override
    public AppResponse updateOrderStatus(OrderVo orderVo) {
        int i = orderCenterDao.updateOrderStatus(orderVo);
        if (i==0){
            if (orderVo.getOrderState().equals("3")){
                return AppResponse.bizError("确认收货失败,请重试");
            }else {
                return AppResponse.bizError("撤销订单失败,请重试");
            }
        }else {
            if (orderVo.getOrderState().equals("3")){
                return AppResponse.success("确认收货成功");
            }else {
                return AppResponse.success("撤销订单成功");
            }
        }
    }


}
