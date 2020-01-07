package com.neusoft.menu.dao;

import com.neusoft.entity.menu.Menu;
import com.neusoft.entity.menu.MenuVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ClassName MenuDao
 * @Description 菜单管理
 * @Author xywang
 * @Date 2019/4/7
 */
@Mapper
public interface MenuDao {

    /**
     * 查询全部菜单
     * @return 菜单集合
     */
    List<MenuVO> listMenus();

    /**
     * 删除菜单
     * @param menu 菜单信息
     * @return
     */
    int deleteMenu(Menu menu);


    /**
     * 更新父级菜单类型为目录
     * @param menu 菜单信息
     * @return
     */
    int updateParentType(Menu menu);

    /**
     * 更新父级菜单类型为菜单
     * @param menu 菜单信息
     * @return
     */
    int updateParentTypes(Menu menu);
    int isExitChildMenu(Menu menu);

    /**
     * 获取菜单范围代码
     * @param menu 菜单信息
     * @return
     */
    String getAuthCode(Menu menu);

    /**
     * 新增菜单
     * @param menu 菜单信息
     * @return
     */
    int insertMenu(Menu menu);

    /**
     * 修改菜单
     * @param menu 菜单信息
     * @return
     */
    int updateMenu(Menu menu);

}
