package com.neusoft.category.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neusoft.category.dao.CategoryDao;
import com.neusoft.category.service.CategoryService;
import com.neusoft.config.RedisUtils;
import com.neusoft.entity.category.Category;
import com.neusoft.entity.category.CategoryVO;
import com.neusoft.entity.common.Base.Tree;
import com.neusoft.entity.common.response.AppResponse;
import com.neusoft.util.StringUtil;
import com.neusoft.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 功能：商品分类管理业务实现类
 * 作成者：林进华
 * 作成时间：2019/4/21
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private CategoryDao categoryDao;
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private ObjectMapper objectMapper;

    private String categoryRootId = "000";

    // 商品分类的树在redis中缓存的key
    private static final String CATEGORY_TREE_CACHED_KEY = "categoryTree";

    /**
     * @Dept：第四组
     * @Description：查询全部商品分类
     * @Author：林进华
     * @Date: 2019/4/20
     * @Return：com.neusoft.common.response.AppResponse
     */
    @Override
    public AppResponse categoryList() {
        AppResponse appResponse;
        // 从redis获取菜单树缓存
        String treeCached = redisUtils.get(CATEGORY_TREE_CACHED_KEY);
        // redis中的categoryTree不为空，则直接从redis缓存中获取
        if(treeCached != null && !"".equals(treeCached.trim())) {
            // 直接从redis缓存中获取
            ArrayList<Tree> children = null;
            try {
                // json转对象
                children = objectMapper.readValue(treeCached, ArrayList.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            appResponse = AppResponse.success("查询成功", children);
        }else {
            // 构建树根
            Tree root = new Tree();
            // 设置父类id
            root.setId(categoryRootId);
            // 创建一个集合，用来存储递归生成的树节点
            List<CategoryVO> categoryList = categoryDao.getCategoryList();
            initTree(root, categoryList);
            // 不要最顶层的节点，即categoryRootId为000的节点，则categoryRootId的子节点就是一级类目的节点
            List<Tree> children = root.getChildren();
            try {
                // 将对象转换成json
                treeCached = objectMapper.writeValueAsString(children);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            // 将商品种类树存入redis
            redisUtils.set(CATEGORY_TREE_CACHED_KEY, treeCached);
            appResponse = AppResponse.success("查询成功", children);
        }
        return appResponse;
    }

    /**
     * @Dept：第四组
     * @Description：递归生成商品种类树
     * @Author：林进华
     * @Date: 2019/4/20
     * @Param：tree 父级树
     * @Param：categoryList 商品分类信息集合
     */
    private void initTree(Tree root, List<CategoryVO> categoryList) {
        // 遍历集合中的节点，则递归结束的条件为集合遍历结束，时间复杂度为( N * N )
        for(CategoryVO category : categoryList) {
            String rootId = root.getId();
            // 判断该对象的父节点是否是root，不是的话则跳过该对象，进行下一个对象的遍历
            if(rootId.equals(category.getCategoryParentId())) {
                Tree child = new Tree();
                // 初始化成树节点
                categoryToTree(child, category);
                // 添加到root对象的孩子集合中
                root.getChildren().add(child);
                // 对该孩子对象进行递归，生成子对象的商品种类树，并在root的孩子集合中添加该孩子对象
                initTree(child, categoryList);
            }
        }
    }

    /**
     * @Dept：第四组
     * @Description：商品种类转换成树
     * @Author：林进华
     * @Date: 2019/4/20
     * @Param：tree 父级树
     * @Param：categoryVO 商品分类信息
     */
    private void categoryToTree(Tree tree, CategoryVO categoryVO) {
        // 为树节点设置商品分类的id
        tree.setId(categoryVO.getCategoryId());
        // 为树节点设置商品分类的父类id
        tree.setPid(categoryVO.getCategoryId());
        // 为树节点设置商品分类的名字name
        tree.setLabel(categoryVO.getCategoryName());
        // 为树节点设置商品分类的层级level
        tree.setType(categoryVO.getCategoryLevel());
        // 为树节点设置商品分类的实体对象
        tree.setAttributes(categoryVO);
    }

    /**
     * @Dept：第四组
     * @Description：添加商品分类
     * @Author：林进华
     * @Date: 2019/4/20
     * @Param：category 商品类别信息
     * @Return：com.neusoft.common.response.AppResponse
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public AppResponse addCategory(Category category) {
        AppResponse response = AppResponse.success("添加成功");

        // 商品种类第一层的处理
        if(category.getCategoryParentId() == null || "".equals(category.getCategoryParentId().trim())) {
            // 设置父节点的id为categoryRootId（categoryRootId = 000）
            category.setCategoryParentId(categoryRootId);
            // 获取商品种类范围代码
            String treeCode = categoryDao.getCategoryTreeCode(category);
            // 设置商品种类的层级为1
            category.setCategoryLevel("1").setCategoryTreeCode(treeCode);
        }else {
            // 获取商品种类的层级
            String level = categoryDao.getCategoryLevel(category);
            // 获取商品种类范围代码
            String treeCode = categoryDao.getCategoryTreeCode(category);
            // 设置商品种类的层级为level，level为父类的层级 + 1
            category.setCategoryLevel(level).setCategoryTreeCode(treeCode);
        }

        // UUID设置
        category.setCategoryId(UUIDUtil.uuidStr());
        // 种类添加
        int count = categoryDao.addCategory(category);
        // 添加成功，则删除redis中的商品分类树的缓存数据
        if(count > 0) {
            redisUtils.delete(CATEGORY_TREE_CACHED_KEY);
        }else {
            response = AppResponse.bizError("添加失败");
        }
        return response;
    }

    /**
     * @Dept：第四组
     * @Description：修改商品分类
     * @Author：林进华
     * @Date: 2019/4/20
     * @Param：category 商品类别信息
     * @Return：com.neusoft.common.response.AppResponse
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public AppResponse updateCategory(Category category) {
        AppResponse appResponse = AppResponse.success("修改成功");
        int count = categoryDao.updateCategory(category);
        // 修改成功，则删除redis中的商品种类树的缓存
        if(count > 0) {
            redisUtils.delete(CATEGORY_TREE_CACHED_KEY);
        }else {
            appResponse = AppResponse.bizError("修改失败");
        }
        return appResponse;
    }

    /**
     * @Dept：第四组
     * @Description：删除商品分类
     * @Author：林进华
     * @Date: 2019/4/20
     * @Param：category 商品类别信息
     * @Return：com.neusoft.common.response.AppResponse
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public AppResponse deleteCategory(Category category) {
        AppResponse appResponse = AppResponse.success("删除成功");
        int count = categoryDao.deleteCategory(category);
        // 删除成功，则删除redis中的商品种类树的缓存
        if(count > 0) {
            redisUtils.delete(CATEGORY_TREE_CACHED_KEY);
        }else {
            appResponse = AppResponse.bizError("删除失败");
        }
        return appResponse;
    }
}
