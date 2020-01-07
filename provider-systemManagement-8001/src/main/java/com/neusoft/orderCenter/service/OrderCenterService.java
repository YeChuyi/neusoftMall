package com.neusoft.orderCenter.service;

import com.neusoft.entity.common.response.AppResponse;
import com.neusoft.entity.order.OrderVo;
import org.springframework.stereotype.Service;

import java.util.List;

public interface OrderCenterService {

    AppResponse getOrders(String orderNumber, String orderState, String orderDateStart, String orderDateEnd, String customerId,int pageNum, int pageSize);

    AppResponse getOrderDetail(String orderId);

    AppResponse updateOrderStatus(OrderVo orderVo);
}
