package com.neusoft.user.service;

import com.neusoft.entity.common.response.AppResponse;
import com.neusoft.entity.user.UserBatchVO;
import com.neusoft.entity.user.UserInfo;
import com.neusoft.entity.user.UserInfoVO;

import java.io.UnsupportedEncodingException;

public interface UserService {
    AppResponse saveUser(UserInfo userInfo) throws UnsupportedEncodingException;

    AppResponse listUsers(UserInfoVO userInfo);

    AppResponse deleteUser(UserBatchVO userInfo);

    AppResponse updatePwd(UserInfo userInfo) throws UnsupportedEncodingException;

    AppResponse updateUser(UserInfo userInfo);

    UserInfo getUserById(String userUuid);

    AppResponse getUserByLogin(UserInfoVO userInfo) throws UnsupportedEncodingException;
}
