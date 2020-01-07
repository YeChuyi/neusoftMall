package com.neusoft.commodityCenter.dao;

import com.neusoft.entity.order.OrderDetail;
import com.neusoft.entity.shop.Shop;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ShopDao {

    /**
     * 功能：添加商品进入购物车
     * 作成者：林进华
     * 作成时间：2019/4/21
     */
    int addShoppingCart(Shop shop);

    /**
     * 功能：订单提交成功后，通过订单明细集合修改购物车的商品
     * 作成者：林进华
     * 作成时间：2019/4/21
     */
    int deleteShoppingCartByOrderDetail(@Param("orderDetailList") List<OrderDetail> orderDetailList);
}
