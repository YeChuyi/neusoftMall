package com.neusoft.menu.service;


import com.neusoft.entity.common.Base.Tree;
import com.neusoft.entity.common.response.AppResponse;
import com.neusoft.entity.menu.Menu;

public interface MenuService {
    /**
     * 部门：南京软件研发中心
     * 功能：查询全部菜单
     * 作成者：xywang
     * 作成时间：2019/4/8
     */
    Tree listMenus();
    /**
     * 部门：南京软件研发中心
     * 功能：删除菜单
     * 描述：略
     * 作成者：xywang
     * 作成时间：2019/4/8
     */
    void deleteMenu(Menu menu);

    /**
     * 部门：南京软件研发中心
     * 功能：新增菜单
     * 描述：略
     * 作成者：xywang
     * 作成时间：2019/4/8
     */
    AppResponse insertMenu(Menu menu);

    /**
     * 部门：南京软件研发中心
     * 功能：修改菜单
     * 描述：略
     * 作成者：xywang
     * 作成时间：2019/4/8
     */
    AppResponse updateMenu(Menu menu);

}
