package com.neusoft.commodityCenter.dao;

import com.neusoft.entity.order.Order;
import com.neusoft.entity.shop.Shop;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderDao {

    /**
     * 功能：订单添加
     * 作成者：林进华
     * 作成时间：2019/4/21
     */
    int addOrder(Order order);

    /**
     * 功能：得到订单的总金额
     * 作成者：林进华
     * 作成时间：2019/4/21
     */
    String getOrderPriceById(@Param("orderId") String orderId);

}
