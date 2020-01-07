package com.neusoft.menu.controller;

import com.neusoft.config.RedisUtils;
import com.neusoft.entity.common.Base.Tree;
import com.neusoft.entity.common.response.AppResponse;
import com.neusoft.entity.menu.Menu;
import com.neusoft.entity.menu.MenuVO;
import com.neusoft.menu.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @ClassName MenuController
 * @Description 菜单管理
 * @Author xywang
 * @Date 2019/4/8
 */
@RestController
@RequestMapping("backend/menu")
@Validated
@Api("菜单管理")
public class MenuController {

    private static final Logger logger = LoggerFactory.getLogger(MenuController.class);

    @Resource
    private MenuService menuService;

    @Autowired
    private RedisUtils redisUtils;

    /**
     * 部门：南京软件研发中心
     * 功能：获取菜单
     * 描述：略
     * 作成者：xywang
     * 作成时间：2019/4/8
     */
    @ApiOperation(value ="获取菜单")
    @RequestMapping(value = "listMenus",method = RequestMethod.GET)
    public AppResponse listMenus() throws Exception {
        try {
            Tree tree = menuService.listMenus();
            return AppResponse.success("查询成功", tree.getChildren());
        } catch (Exception e) {
            logger.error("菜单获取异常", e);
            throw new Exception("菜单获取失败，请重试");
        }
    }

    /**
     * 部门：南京软件研发中心
     * 功能：删除菜单
     * 描述：略
     * 作成者：xywang
     * 作成时间：2019/4/8
     */
    @ApiOperation(value ="删除菜单")
    @RequestMapping(value = "deleteMenu",method = RequestMethod.PUT)
    public AppResponse deleteMenu(@Valid MenuVO menuVO) {
        try {
            //获取用户id
            String userId = redisUtils.get(menuVO.getToken()+"_id");
            menuVO.setLastModifiedBy(userId);
            menuVO.setCreateBy(userId);
            Menu menu = new Menu();
            BeanUtils.copyProperties(menuVO, menu);
            menuService.deleteMenu(menu);
            return AppResponse.success("菜单删除成功");
        } catch (Exception e) {
            logger.error("菜单删除错误", e);
            return AppResponse.notFound("删除失败");
        }
    }

    /**
     * 部门：南京软件研发中心
     * 功能：新增菜单
     * 描述：略
     * 作成者：xywang
     * 作成时间：2019/4/8
     */
    @ApiOperation(value ="新增菜单")
    @RequestMapping(value = "saveMenu",method = RequestMethod.POST)
    public AppResponse saveMenu(@Valid MenuVO menuVO) {
        try {
            //获取用户id
            String userId = redisUtils.get(menuVO.getToken()+"_id");
            menuVO.setLastModifiedBy(userId);
            menuVO.setCreateBy(userId);
            Menu menu = new Menu();
            BeanUtils.copyProperties(menuVO, menu);
            return menuService.insertMenu(menu);
        } catch (Exception e) {
            logger.error("菜单新增异常", e);
            return AppResponse.notFound("新增失败");
        }
    }

    /**
     * 部门：南京软件研发中心
     * 功能：修改菜单
     * 描述：略
     * 作成者：xywang
     * 作成时间：2019/4/8
     */
    @ApiOperation(value ="修改菜单")
    @RequestMapping(value = "updateMenu",method = RequestMethod.PUT)
    public AppResponse updateMenu(@Valid MenuVO menuVO) {
        try {
            //获取用户id
            String userId = redisUtils.get(menuVO.getToken()+"_id");
            menuVO.setLastModifiedBy(userId);
            menuVO.setCreateBy(userId);
            Menu menu = new Menu();
            BeanUtils.copyProperties(menuVO, menu);
            return menuService.updateMenu(menu);
        } catch (Exception e) {
            logger.error("菜单修改异常", e);
            return AppResponse.notFound("修改失败");
        }
    }

}
