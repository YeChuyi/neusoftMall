package com.neusoft.shoppingCart.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.entity.common.Base.PageVo;
import com.neusoft.entity.common.response.AppResponse;
import com.neusoft.entity.shop.Shop;
import com.neusoft.shoppingCart.dao.ShoppingCartDao;
import com.neusoft.shoppingCart.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Resource
    ShoppingCartDao shoppingCartDao;

    @Override
    public AppResponse getShoppingCartList(Shop shop) {
        List<Shop> shopList = shoppingCartDao.shopList(shop);
        PageVo pageVo = new PageVo();
        pageVo.setList(shopList);
        PageHelper.startPage(1,10);
        if(shopList==null || shopList.size()==0){
            return AppResponse.notFound("没有该条记录");
        }else {
            pageVo.setTotalRecords((int) new PageInfo<Shop>(shopList).getTotal());
        }
        return AppResponse.success("查询成功",shopList);
    }

    @Override
    public AppResponse deleteShoppingCart(Shop shop) {
        AppResponse appResponse = AppResponse.success("删除成功");
        int count = shoppingCartDao.deleteShop(shop);
        if(count == 0) {
            appResponse = AppResponse.bizError("删除失败");
        }
        return appResponse;
    }
}
