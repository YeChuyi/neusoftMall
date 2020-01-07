package com.neusoft.order.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.config.RedisUtils;
import com.neusoft.entity.common.Base.PageVo;
import com.neusoft.entity.common.response.AppResponse;
import com.neusoft.entity.order.Order;
//import com.neusoft.order.dao.OrderDetialMapper;
import com.neusoft.entity.order.OrderDetailVO;
import com.neusoft.entity.order.OrderVo;
import com.neusoft.order.dao.OrderDao;
import com.neusoft.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ClassName: OrderServiceImpl
 * @Description: 订单管理
 * @Author: 蔡程健
 * @Date: 2019/4/22
 */
@Transactional
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    OrderDao orderDao;

    @Autowired
    RedisUtils redisUtils;

    /**
     * @Description：查询订单
     * @Author：蔡程健
     * @Date: 2019/4/24
     * @param orderNumber
     * @param orderState
     * @param orderDateStart
     * @param orderDateEnd
     * @param pageNum
     * @param pageSize
     * @return com.neusoft.common.response.AppResponse
     */
    @Override
    public AppResponse getOrders(String orderNumber,String orderState,String orderDateStart,String orderDateEnd,int pageNum,int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Order> orderList = orderDao.getOrders(orderNumber,orderState,orderDateStart,orderDateEnd);
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
     * @Description：查询订单详情
     * @Author：蔡程健
     * @Date: 2019/4/24
     * @param orderId
     * @return com.neusoft.common.response.AppResponse
     */
    @Override
    public AppResponse getOrderDetail(String orderId) {
        OrderVo orderVo = orderDao.getOrderVo(orderId);
        if (orderVo==null || orderVo.equals(null) || orderVo.equals("")){
            return AppResponse.notFound("没有该条记录");
        }else{
            List<OrderDetailVO> orderDetailList = orderDao.getOrderDetail(orderId);
            orderVo.setCommodityList(orderDetailList);
            return AppResponse.success("查询成功",orderVo);
        }
    }


    /**
     * @Description：查看订单详情
     * @Author：蔡程健
     * @Date: 2019/4/24
     * @param orderVo
     * @return com.neusoft.common.response.AppResponse
     */
    @Override
    public AppResponse updateOrderStatus(OrderVo orderVo) {
        int i = orderDao.updateOrderStatus(orderVo);
        if (i==0){
            return AppResponse.bizError("订单发货失败,请重试");
        }
        return AppResponse.success("订单发货成功");
}
}
