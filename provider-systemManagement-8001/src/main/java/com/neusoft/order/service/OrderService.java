package com.neusoft.order.service;

import com.neusoft.entity.common.response.AppResponse;
import com.neusoft.entity.order.*;

import java.io.UnsupportedEncodingException;
import java.util.List;

public interface OrderService {

    AppResponse getOrders(String orderNumber,String orderState,String orderDateStart,String orderDateEnd,int pageNum,int pageSize);

    AppResponse getOrderDetail(String orderId);

    AppResponse updateOrderStatus(OrderVo orderVo);

}
