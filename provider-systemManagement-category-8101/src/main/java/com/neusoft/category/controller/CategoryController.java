package com.neusoft.category.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neusoft.category.service.CategoryService;
import com.neusoft.config.RedisUtils;
import com.neusoft.entity.category.Category;
import com.neusoft.entity.category.CategoryVO;
import com.neusoft.entity.common.response.AppResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("mall/backend/category")
@Api("商品类别管理")
public class CategoryController {

    private Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    CategoryService categoryService;
    @Autowired
    RedisUtils redisUtils;

    /**
     * @Dept：第四组
     * @Description：商品种类获取
     * @Author：林进华
     * @Date: 2019/4/20
     * @Return：com.neusoft.common.response.AppResponse
     */
    @RequestMapping(value = "/getCategory", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    @ApiOperation("查询商品种类树")
    public AppResponse categoryList() {
        try {
            return categoryService.categoryList();
        }catch(Exception e) {
            logger.error("商品种类获取异常", e);
            return AppResponse.bizError("获取失败");
        }
    }

    /**
     * @Dept：第四组
     * @Description：商品种类添加
     * @Author：林进华
     * @Date: 2019/4/20
     * @Param：categoryVO 商品类别信息
     * @Return：com.neusoft.common.response.AppResponse
     */
    @RequestMapping(value = "/addCategory", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ApiOperation("商品种类添加")
    public AppResponse addCategory(@Validated CategoryVO categoryVO) {
        try {
            String userId = redisUtils.get(categoryVO.getToken()+"_id");
            Category category = new Category();
            BeanUtils.copyProperties(categoryVO, category);
            category.setCreatedBy(userId);
            category.setLastModifiedBy(userId);
            return categoryService.addCategory(category);
        }catch(Exception e) {
            logger.error("商品种类添加异常", e);
            return AppResponse.notFound("添加失败");
        }
    }

    /**
     * @Dept：第四组
     * @Description：商品分类修改
     * @Author：林进华
     * @Date: 2019/4/20
     * @Param：categoryVO 商品类别信息
     * @Return：com.neusoft.common.response.AppResponse
     */
    @RequestMapping(value = "/updateCategory", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ApiOperation("商品分类修改")
    public AppResponse updateCategory(CategoryVO categoryVO) {
        try {
            String userId = redisUtils.get(categoryVO.getToken()+"_id");
            Category category = new Category();
            BeanUtils.copyProperties(categoryVO, category);
            category.setLastModifiedBy(userId);
            return categoryService.updateCategory(category);
        }catch(Exception e) {
            logger.error("商品分类修改异常", e);
            return AppResponse.notFound("修改失败");
        }
    }

    /**
     * @Dept：第四组
     * @Description：商品分类删除
     * @Author：林进华
     * @Date: 2019/4/20
     * @Param：categoryVO 商品类别信息
     * @Return：com.neusoft.common.response.AppResponse
     */
    @RequestMapping(value = "/deleteCategory", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ApiOperation("商品分类删除")
    public AppResponse deleteCategory(CategoryVO categoryVO) throws Exception {
        try {
            String userId = redisUtils.get(categoryVO.getToken()+"_id");
            Category category = new Category();
            BeanUtils.copyProperties(categoryVO, category);
            category.setLastModifiedBy(userId);
            return categoryService.deleteCategory(category);
        }catch(Exception e) {
            logger.error("商品分类删除异常", e);
            return AppResponse.notFound("删除失败");
        }
    }

}
