package com.neusoft.user.controller;

import com.neusoft.config.RedisUtils;
import com.neusoft.entity.common.response.AppResponse;
import com.neusoft.entity.user.UserBatchVO;
import com.neusoft.entity.user.UserInfo;
import com.neusoft.entity.user.UserInfoVO;
import com.neusoft.user.service.UserService;
import com.neusoft.util.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @ClassName: UserController
 * @Description:
 * @Author: shengtt
 * @Date: 2019/4/1
 */
@RestController
@RequestMapping("backend/user")
@Slf4j
@Api("用户管理")
public class UserController {

    @Autowired
    private RedisUtils redisUtils;

    @Resource
    private UserService userService;

    /**
     * @Dept：南京软件研发中心
     * @Description：新增用户
     * @Author：shengtt
     * @Date: 2019/4/2
     * @Param：userInfo
     * @Return：com.neusoft.common.response.AppResponse
     */
    @ApiOperation(value ="保存用户")
    @PostMapping("saveUser")
    public AppResponse saveUser(@Valid UserInfo userInfo) throws Exception {
        try {
            //获取用户id
            String userId = redisUtils.get(userInfo.getToken()+"_id");
            userInfo.setCreatedBy(userId);
            AppResponse appResponse = userService.saveUser(userInfo);
            return appResponse;
        } catch (Exception e) {
            log.error("用户新增失败", e);
            throw new Exception("用户新增失败，请重试");
        }
    }

    /**
     * @Dept：南京软件研发中心
     * @Description：获取用户列表
     * @Author：shengtt
     * @Date: 2019/4/2
     * @Param：userInfo
     * @Return：com.neusoft.common.response.AppResponse
     */
    @ApiOperation(value ="获取用户列表")
    @GetMapping(value = "listUsers")
    public AppResponse listUsers(UserInfoVO userInfo) throws Exception {
        try {
//            AppResponse.success("查询成功",userService.listUsers(userInfo));
            return AppResponse.success("查询成功",userService.listUsers(userInfo));
        } catch (Exception e) {
            log.error("用户获取异常", e);
            throw new Exception("查询错误，请重试");
        }
    }

    /**
     * @Dept：南京软件研发中心
     * @Description：删除用户信息
     * @Author：shengtt
     * @Date: 2019/4/2
     * @Param：userInfo
     * @Return：com.neusoft.common.response.AppResponse
     */
    @ApiOperation(value ="删除用户")
    @PostMapping("deleteUser")
    public AppResponse deleteUser(UserBatchVO userInfo) throws Exception {
        try {
            //获取用户id
            String userId = redisUtils.get(userInfo.getToken()+"_id");
            userInfo.setLastModifiedBy(userId);
            return userService.deleteUser(userInfo);
        } catch (Exception e) {
            log.error("用户删除错误", e);
            throw new Exception("用户删除错误");
        }
    }

    /**
     * @Dept：南京软件研发中心
     * @Description：修改密码
     * @Author：shengtt
     * @Date: 2019/4/2
     * @Param：userInfo
     * @Return：com.neusoft.common.response.AppResponse
     */
    @ApiOperation(value ="修改密码")
    @PostMapping("updatePwd")
    public AppResponse updatePwd(UserInfo userInfo) throws Exception {
        try {
            ///获取用户id
            String userId = redisUtils.get(userInfo.getToken()+"_id");
            userInfo.setLastModifiedBy(userId);
            return userService.updatePwd(userInfo);
        } catch (Exception e) {
            log.error("修改异常", e);
            throw new Exception("修改密码失败，请重试");
        }
    }
    /**
     * @Dept：南京软件研发中心
     * @Description：修改用户
     * @Author：shengtt
     * @Date: 2019/4/3
     * @Param：userInfo
     * @Return：com.neusoft.common.response.AppResponse
     */
    @ApiOperation(value ="修改用户")
    @PostMapping("updateUser")
    public AppResponse updateUser(UserInfo userInfo) throws Exception {
        try {
            //获取用户id
            String userId = redisUtils.get(userInfo.getToken()+"_id");
            userInfo.setLastModifiedBy(userId);
            return userService.updateUser(userInfo);
        } catch (Exception e) {
            log.error("修改用户信息错误", e);
            throw new Exception("系统错误，请重试");
        }
    }

    /**
     * @Dept：南京软件研发中心
     * @Description：根据userUuid查询用户信息
     * @Author：shengtt
     * @Date: 2019/4/3
     * @Param：userUuid
     * @Return：com.neusoft.common.response.AppResponse
     */
    @ApiOperation(value ="根据userUuid查询用户信息")
    @GetMapping(value = "getUserByUserUuid/{userUuid}")
    public AppResponse getUserByUserCode(@PathVariable("userUuid") String userUuid) throws Exception {
        UserInfo userInfo = null;
        try {
            userInfo = userService.getUserById(userUuid);
        } catch (Exception e) {
            log.error("用户查询错误", e);
            throw new Exception("查询错误，请重试");
        }
        if (userInfo == null) {
            return AppResponse.notFound("无查询结果");
        }
        return AppResponse.success("查询成功", userInfo);
    }

    /**
     * @Dept：南京软件研发中心
     * @Description：删除用户信息
     * @Author：xywang
     * @Date: 2019/4/2
     * @Param：userInfo
     * @Return：com.neusoft.common.response.AppResponse
     */
    @ApiOperation(value ="登录")
    @GetMapping("getUserByLogin")
    public AppResponse getUserByLogin(UserInfoVO userInfo) throws Exception {
        try {
            return userService.getUserByLogin(userInfo);
        } catch (Exception e) {
            log.error("登录错误", e);
            throw new Exception("登录错误");
        }
    }

    /**
     * @Dept：南京软件研发中心
     * @Description：删除用户信息
     * @Author：xywang
     * @Date: 2019/4/2
     * @Param：token
     * @Return：com.neusoft.common.response.AppResponse
     */
    @ApiOperation(value ="登出")
    @GetMapping("updLoginOut")
    public AppResponse updLoginOut(@RequestParam("token") String token) throws Exception {
        try {
            redisUtils.delete(token+"_id");
            redisUtils.delete(token+"_account");
            redisUtils.delete(token+"_name");
            return AppResponse.success("登出成功");
        } catch (Exception e) {
            log.error("登出错误", e);
            throw new Exception("登出错误");
        }
    }
}
