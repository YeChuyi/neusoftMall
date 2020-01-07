package com.neusoft.order.dao;

import com.neusoft.entity.order.*;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderDao {
    /**
     * @Description：查询订单
     * @Author：蔡程健
     * @Date: 2019/4/24
     * @param orderNumber
     * @param orderState
     * @param orderDateStart
     * @param orderDateEnd
     * @return List<Order>
     */
    List<Order> getOrders(@Param("orderNumber") String orderNumber,@Param("orderState") String orderState,@Param("orderDateStart") String orderDateStart,@Param("orderDateEnd") String orderDateEnd);

    /**
     * @Description：查询订单详情
     * @Author：蔡程健
     * @Date: 2019/4/24
     * @param orderId
     * @return List<OrderDetailVO>
     */
    List<OrderDetailVO> getOrderDetail(String  orderId);

    /**
     * @Description：查询订单，通过订单id查找具体的订单
     * @Author：蔡程健
     * @Date: 2019/4/24
     * @param orderId
     * @return OrderVo
     */
    OrderVo getOrderVo(String orderId);

    /**
     * @Description：订单发货，修改订单的状态
     * @Author：蔡程健
     * @Date: 2019/4/24
     * @param orderVo
     * @return int
     */
    int updateOrderStatus(OrderVo orderVo);
}