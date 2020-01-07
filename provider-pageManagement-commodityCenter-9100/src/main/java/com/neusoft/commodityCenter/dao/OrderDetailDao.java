package com.neusoft.commodityCenter.dao;

import com.neusoft.entity.order.OrderDetail;
import com.neusoft.entity.shop.Shop;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderDetailDao {

    /**
     * 功能：订单明细列表添加
     * 作成者：林进华
     * 作成时间：2019/4/21
     */
    int addOrderDetail(@Param("orderDetailList") List<OrderDetail> orderDetailList);

}
