package com.neusoft.orderCenter.dao;

import com.neusoft.entity.order.Order;
import com.neusoft.entity.order.OrderDetailVO;
import com.neusoft.entity.order.OrderVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderCenterDao {

    /**
     * @Description：查询客户自己的订单
     * @Author：蔡程健
     * @Date: 2019/4/24
     * @param orderNumber
     * @param orderState
     * @param orderDateStart
     * @param orderDateEnd
     * @param customerId
     * @return List<Order>
     */
    List<Order> getOrderList(@Param("orderNumber") String orderNumber,@Param("orderState") String orderState,@Param("orderDateStart") String orderDateStart,@Param("orderDateEnd") String orderDateEnd,@Param("customerId") String customerId);

    /**
     * @Description：查询客户自己的订单详情
     * @Author：蔡程健
     * @Date: 2019/4/24
     * @param orderId
     * @return List<OrderDetailVO>
     */
    List<OrderDetailVO> getOrderDetail(String  orderId);

    /**
     * @Description：修改客户自己的订单状态
     * @Author：蔡程健
     * @Date: 2019/4/24
     * @param orderVo
     * @return int
     */
    int updateOrderStatus(OrderVo orderVo);

    /**
     * @Description：查询订单，客户通过订单id查找具体的订单
     * @Author：蔡程健
     * @Date: 2019/4/24
     * @param orderId
     * @return OrderVo
     */
    OrderVo getOrderVo(@Param("orderId") String orderId);
}
