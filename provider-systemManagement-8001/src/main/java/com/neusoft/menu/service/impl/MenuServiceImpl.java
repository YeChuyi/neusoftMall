package com.neusoft.menu.service.impl;

import com.neusoft.entity.common.Base.Tree;
import com.neusoft.entity.common.response.AppResponse;
import com.neusoft.entity.menu.Menu;
import com.neusoft.entity.menu.MenuVO;
import com.neusoft.menu.dao.MenuDao;
import com.neusoft.menu.service.MenuService;
import com.neusoft.util.StringUtil;
import com.neusoft.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;

/**
 * @ClassName MenuService
 * @Description 菜单管理
 * @Author xywang
 * @Date 2019/4/8
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Resource
    private  MenuDao menuDao;

    @Value("${rootId}")
    private String rootId;


    /**
     * 部门：南京软件研发中心
     * 功能：查询全部菜单
     * 作成者：xywang
     * 作成时间：2019/4/8
     */
    @Override
    public Tree listMenus() {

        List<MenuVO> menuList = menuDao.listMenus();
        Tree rootTree = new Tree();
        initTree(rootTree, menuList, rootId);

        return rootTree;
    }

    /**
     * 部门：南京软件研发中心
     * 功能：初始化菜单树
     * 描述：略
     * 作成者：xywang
     * 作成时间：2019/4/8
     */
    private void initTree(Tree rootTree, List<MenuVO> allMenus, String selfCode){
        Iterator<MenuVO> menuIterator = allMenus.iterator();
        while(menuIterator.hasNext()){
            MenuVO temp = menuIterator.next();
            System.out.println("第一次temp"+ " "+temp );
            System.out.println("第一次rootTree"+" "+rootTree);
            //初始化自身节点
            if(temp.getMenuId().equals(selfCode)){
                menuToTree(temp,rootTree);
                System.out.println("第二次rootTree"+" "+rootTree);
            }else
                if(temp.getParentMenuId().equals(selfCode)){
                //初始化子节点
                Tree children = new Tree();
                menuToTree(temp,children);

                rootTree.getChildren().add(children);
                System.out.println(rootTree);
                System.out.println("递归传入的值"+" "+temp.getMenuId());
                //递归
                initTree(children,allMenus,temp.getMenuId());
            }
        }
    }

    /**
     * 部门：南京软件研发中心
     * 功能：将菜单格式化成树
     * 描述：略
     * 作成者：xywang
     * 作成时间：2019/4/8
     */
    private void menuToTree(MenuVO menu, Tree tree){
        tree.setId(menu.getMenuId());
        tree.setIndex(menu.getMenuUrl());
        tree.setPid(menu.getParentMenuId());
        tree.setLabel(menu.getMenuName());
        tree.setType(menu.getMenuType());
        tree.setAttributes(menu);
    }

    /**
     * 部门：南京软件研发中心
     * 功能：删除菜单
     * 描述：略
     * 作成者：xywang
     * 作成时间：2019/4/8
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteMenu(Menu menu) {

        // 删除菜单
        menuDao.deleteMenu(menu);
        //更新父级菜单类型为菜单
        int num = menuDao.isExitChildMenu(menu);
        if(!rootId.equals(menu.getParentMenuId())&&!"".equals(menu.getParentMenuId())&&null!=menu.getParentMenuId()&&0>=num){
            menuDao.updateParentTypes(menu);
        }

    }

    /**
     * 部门：南京软件研发中心
     * 功能：新增菜单
     * 描述：略
     * 作成者：xywang
     * 作成时间：2019/4/8
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public AppResponse insertMenu(Menu menu) {
        AppResponse appResponse = AppResponse.success("新增成功！");
        // 菜单代码
        String menuId = StringUtil.getCommonCode(2);
        // 根菜单就是菜单代码，其他新建子菜单时，获取父菜单的范围代码+‘3位自增长数’。
        if(null == menu.getParentMenuId() || "".equals(menu.getParentMenuId()) ) {
            menu.setAuthCode(menuId);
            menu.setParentMenuId(rootId);
        }else{
            // 获取菜单范围代码
            menu.setAuthCode(menuDao.getAuthCode(menu));
        }
        menu.setId(UUIDUtil.uuidStr());
        menu.setMenuId(menuId);
        // 新增菜单
        int count = menuDao.insertMenu(menu);
        if(0 == count) {
            appResponse = AppResponse.bizError("新增失败，请重试！");
        }
        //更新父级菜单类型为目录
        if(!rootId.equals(menu.getParentMenuId())&&!"".equals(menu.getParentMenuId())&&null!=menu.getParentMenuId()){
            menuDao.updateParentType(menu);
        }
        return appResponse;
    }

    /**
     * 部门：南京软件研发中心
     * 功能：修改菜单
     * 描述：略
     * 作成者：xywang
     * 作成时间：2019/4/8
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public AppResponse updateMenu(Menu menu) {
        AppResponse appResponse = AppResponse.success("修改成功！");
        int count = menuDao.updateMenu(menu);
        if(0 == count) {
            appResponse = AppResponse.bizError("修改失败，请重试！");
        }
        return appResponse;
    }


}
