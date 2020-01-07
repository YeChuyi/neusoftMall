package com.neusoft.shoppingCart.dao;

import com.neusoft.entity.shop.Shop;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShoppingCartDao {
    /**
     * 功能：购物车商品查询
     * 作成者：cqluo
     * 作成时间：2019/4/22
     */
    List<Shop> shopList(Shop shop);

    /**
     * 功能：购物车商品删除
     * 作成者：cqluo
     * 作成时间：2019/4/22
     */
    int deleteShop(Shop shop);
}
