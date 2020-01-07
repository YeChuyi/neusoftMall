package com.neusoft.entity.commodityCenter;

import com.neusoft.entity.common.Base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @ClassName: CommodityHistory
 * @Description: 商品购买历史类
 * @Author: 林进华
 * @Date: 2019/4/21
 */
@NoArgsConstructor
@Data
@Accessors(chain = true)
@EqualsAndHashCode
public class CommodityCenterTradingVO extends BaseEntity {

    /**
     * 客户姓名
     */
    private String customerName;

    /**
     * 商品单价
     */
    private String commodityPrice;

    /**
     * 购买数量
     */
    private String commodityNum;

}
