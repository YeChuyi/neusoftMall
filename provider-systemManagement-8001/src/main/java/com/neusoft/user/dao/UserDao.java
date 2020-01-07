package com.neusoft.user.dao;

import com.neusoft.entity.user.UserBatchVO;
import com.neusoft.entity.user.UserInfo;
import com.neusoft.entity.user.UserInfoVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {
    /**
     * @Dept：南京软件研发中心
     * @Description：统计用户账号数量
     * @Author：shengtt
     * @Date: 2019/4/2
     * @Param：userInfo
     * @Return：int
     */
    int countUserAcct(UserInfo userInfo);

    /**
     * @Dept：南京软件研发中心
     * @Description：新增用户
     * @Author：shengtt
     * @Date: 2019/4/2
     * @Param：userInfo
     * @Return：int
     */
    int saveUser(UserInfo userInfo);

    /**
     * @Dept：南京软件研发中心
     * @Description：获取所有用户信息
     * @Author：shengtt
     * @Date: 2019/4/2
     * @Param：userInfo
     * @Return：int
     */
    List<UserInfo> listUsers(UserInfoVO userInfo);

    /**
     * @Dept：南京软件研发中心
     * @Description：删除用户
     * @Author：shengtt
     * @Date: 2019/4/2
     * @Param：userInfo
     * @Return：int
     */
    int deleteUser(UserBatchVO userInfo);

    /**
     * @Dept：南京软件研发中心
     * @Description：根据uuid获取用户信息
     * @Author：shengtt
     * @Date: 2019/4/2
     * @Param：userUuid
     * @Return：com.neusoft.admin.user.entity.UserInfo
     */
    UserInfo getUserById(String userUuid);

    /**
     * @Dept：南京软件研发中心
     * @Description：修改密码
     * @Author：shengtt
     * @Date: 2019/4/2
     * @Param：userInfo
     * @Return：int
     */
    int updateUserPwd(UserInfo userInfo);
    /**
     * @Dept：南京软件研发中心
     * @Description：修改用户
     * @Author：shengtt
     * @Date: 2019/4/3
     * @Param：userInfo
     * @Return：com.neusoft.common.response.AppResponse
     */
    int updateUser(UserInfo userInfo);

    /**
     * @Dept：南京软件研发中心
     * @Description：登录
     * @Author：xywang
     * @Date: 2019/4/2
     * @Param：userInfo
     * @Return：com.neusoft.admin.user.entity.UserInfo
     */
    List<UserInfo> getUserByLogin(UserInfoVO userInfo);
}
