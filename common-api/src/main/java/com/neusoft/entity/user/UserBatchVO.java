package com.neusoft.entity.user;

import com.neusoft.entity.common.Base.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: UserBatchVO
 * @Description: 用户批量操作
 * @Author: shengtt
 * @Date: 2019/4/2
 */
@Data
public class UserBatchVO extends BaseEntity implements Serializable {

    private List<String> userList;//用户id列表
    private String token;
}
