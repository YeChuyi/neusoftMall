package com.neusoft.shoppingCart.service;

import com.neusoft.entity.common.response.AppResponse;
import com.neusoft.entity.shop.Shop;

public interface ShoppingCartService {
    AppResponse getShoppingCartList(Shop shop);
    AppResponse deleteShoppingCart(Shop shop);
}
