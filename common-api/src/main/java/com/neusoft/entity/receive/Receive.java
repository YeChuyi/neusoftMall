package com.neusoft.entity.receive;

import com.neusoft.entity.common.Base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 部门：软件开发事业部
 * 描述：收货地址实体类
 * 作成者：yechuyi
 * 作成时间：2018/4/20
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain=true)//启用链式,如dept.setDname("yechuyi").setDb_source("test")
public class Receive extends BaseEntity {
    private String receiveId;//收获地址id
    private String regionProvince;//省ID
    private String regionCity;//市ID
    private String regionCounty;//区ID
    private String receiveDetailedAddress;//详细地址
    private String receiveContact;//联系人
    private String receiveTel;//联系电话
    private String receiveIsDefault;//是否默认，1是0否
    private String token;

}
