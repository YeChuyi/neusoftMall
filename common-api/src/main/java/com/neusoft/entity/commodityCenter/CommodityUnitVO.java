package com.neusoft.entity.commodityCenter;

import com.neusoft.entity.common.Base.BasePageVo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @Author 715
 * @Date 2019/4/23 0:23
 */
@NoArgsConstructor
@Data
@Accessors(chain = true)
@EqualsAndHashCode
public class CommodityUnitVO extends BasePageVo {

    //单位id
    private String unitId;
    //单位名称
    private String unitName;

    private String token;

    private String createdBy;

    private Date gmtCreate;

    private String lastModifiedBy;

    private Date gmtModified;

    private int sortNo;

    private String isDeleted;

    private String version;

//    private String created_by;
//
//    private String gmt_create;
//
//    private String last_modified_by;
//
//    private String gmt_modified;
//
//    private String is_deleted;
//
//    private String sort_no;
//
//    private String version;


}
