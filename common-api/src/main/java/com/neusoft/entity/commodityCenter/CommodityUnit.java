package com.neusoft.entity.commodityCenter;

import com.neusoft.entity.common.Base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @ClassName: CommodityUnit
 * @Description: 商品单位类
 * @Author: 林进华
 * @Date: 2019/4/21
 */
@NoArgsConstructor
@Data
@Accessors(chain = true)
@EqualsAndHashCode
public class CommodityUnit extends BaseEntity {

    /**
     * 单位id
     */
    private String unitId;
    /**
     * 单位名称
     */
    private String unitName;

    /**
     * 用户token
     */
    private String token;

}
