package com.neusoft.category.service;

import com.neusoft.entity.category.Category;
import com.neusoft.entity.common.response.AppResponse;

/**
 * @ClassName: CategoryServiceImpl
 * @Description: 商品分类管理
 * @Author: jhlin
 * @Date: 2019/4/20
 */
public interface CategoryService {

    /**
     * @Dept：第四组
     * @Description：查询全部商品分类
     * @Author：林进华
     * @Date: 2019/4/20
     * @Return：com.neusoft.common.response.AppResponse
     */
    AppResponse categoryList();

    /**
     * @Dept：第四组
     * @Description：添加商品分类
     * @Author：林进华
     * @Date: 2019/4/20
     * @Param：category 商品类别信息
     * @Return：com.neusoft.common.response.AppResponse
     */
    AppResponse addCategory(Category category);

    /**
     * @Dept：第四组
     * @Description：修改商品分类
     * @Author：林进华
     * @Date: 2019/4/20
     * @Param：category 商品类别信息
     * @Return：com.neusoft.common.response.AppResponse
     */
    AppResponse updateCategory(Category category);

    /**
     * @Dept：第四组
     * @Description：删除商品分类
     * @Author：林进华
     * @Date: 2019/4/20
     * @Param：category 商品类别信息
     * @Return：com.neusoft.common.response.AppResponse
     */
    AppResponse deleteCategory(Category category);
}
