package com.neusoft.entity.receive;
import com.neusoft.entity.common.Base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 部门：软件开发事业部
 * 描述：地区实体类
 * 作成者：yechuyi
 * 作成时间：2018/4/20
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain=true)//启用链式,如dept.setDname("yechuyi").setDb_source("test")
public class Region  extends BaseEntity{
    private String regionId;//地域ID
    private String parentRegionId;//上级地域ID
    private String regionType;//地区类型，1省2市3区
    private String regionName;//地区名称
    private String token;
}
