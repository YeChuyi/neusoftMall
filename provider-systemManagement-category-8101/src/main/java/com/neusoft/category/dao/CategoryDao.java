package com.neusoft.category.dao;

import com.neusoft.entity.category.Category;
import com.neusoft.entity.category.CategoryVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryDao {

    /**
     * @Dept：第四组
     * @Description：查询全部商品分类
     * @Author：林进华
     * @Date: 2019/4/20
     * @Return：List<CategoryVO> 商品分类集合
     */
    List<CategoryVO> getCategoryList();

    /**
     * @Dept：第四组
     * @Description：得到商品层级
     * @Author：林进华
     * @Date: 2019/4/20
     * @Param：category 商品类别信息
     * @Return：String 商品分类对应的层级
     */
    String getCategoryLevel(Category category);

    /**
     * @Dept：第四组
     * @Description：得到范围代码
     * @Author：林进华
     * @Date: 2019/4/20
     * @Param：category 商品类别信息
     * @Return：String 商品分类对应的code
     */
    String getCategoryTreeCode(Category category);

    /**
     * @Dept：第四组
     * @Description：添加商品分类
     * @Author：林进华
     * @Date: 2019/4/20
     * @Param：category 商品类别信息
     * @Return：int 商品分类添加成功的条数
     */
    int addCategory(Category category);

    /**
     * @Dept：第四组
     * @Description：修改商品分类
     * @Author：林进华
     * @Date: 2019/4/20
     * @Param：category 商品类别信息
     * @Return：int 商品分类修改成功的条数
     */
    int updateCategory(Category category);

    /**
     * @Dept：第四组
     * @Description：删除商品分类
     * @Author：林进华
     * @Date: 2019/4/20
     * @Param：category 商品类别信息
     * @Return：int 商品分类删除成功的条数
     */
    int deleteCategory(Category category);

}
