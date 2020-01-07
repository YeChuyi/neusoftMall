package com.neusoft.shoppingCart.controller;


import com.neusoft.config.RedisUtils;
import com.neusoft.entity.common.response.AppResponse;
import com.neusoft.entity.shop.Shop;
import com.neusoft.entity.shop.ShopVo;
import com.neusoft.shoppingCart.service.ShoppingCartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/mall/front/shoppingCart")
@Slf4j
@Api("购物车")
public class ShoppingCartController {
    private Logger logger = LoggerFactory.getLogger(ShoppingCartController.class);

    @Resource
    private RedisUtils redisUtils;

    @Resource
    private ShoppingCartService shoppingCartService;

    /**
     * 功能：购物车商品查询
     * 作成者：cqluo
     * 作成时间：2019/4/22
     */
    @ApiOperation("购物车商品查询")
    @GetMapping(value = "/getShoppingCartList", produces = {"application/json;charset=UTF-8"})
    public AppResponse getShoppingCartList(@Valid ShopVo shopVo){
        try{
            String userId = redisUtils.get(shopVo.getToken()+"_id");
            shopVo.setLastModifiedBy(userId);
            shopVo.setCreatedBy(userId);
            Shop shop = new Shop();
            BeanUtils.copyProperties(shopVo,shop);
            return shoppingCartService.getShoppingCartList(shop);
        }catch (Exception e){
            logger.error("购物车商品查询异常", e);
            return AppResponse.bizError("获取失败");
        }
    }

    /**
     * 功能：购物车商品删除
     * 作成者：cqluo
     * 作成时间：2019/4/22
     */
    @PutMapping(value = "/deleteShoppingCart", produces = {"application/json;charset=UTF-8"})
    @ApiOperation("购物车商品删除")
    public AppResponse deleteShoppingCart(@Valid ShopVo shopVo){
        try{
            String userId = redisUtils.get(shopVo.getToken()+"_id");
            shopVo.setLastModifiedBy(userId);
            shopVo.setCreatedBy(userId);
            Shop shop = new Shop();
            BeanUtils.copyProperties(shopVo,shop);
            return shoppingCartService.deleteShoppingCart(shop);
        }catch (Exception e){
            logger.error("购物车商品删除异常", e);
            return AppResponse.bizError("获取失败");
        }
    }
}
