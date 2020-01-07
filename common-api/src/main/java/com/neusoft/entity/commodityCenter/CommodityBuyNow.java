package com.neusoft.entity.commodityCenter;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @ClassName: CommodityBuyNow
 * @Description: 立即购买商品类
 * @Author: 林进华
 * @Date: 2019/4/21
 */
@NoArgsConstructor
@Data
@Accessors(chain = true)
@EqualsAndHashCode
public class CommodityBuyNow {

    /**
     * 商品ID
     */
    private String commodityId;

    /**
     * 商品编号
     */
    private String commodityCode;

    /**
     * 商品首图
     */
    private String pictureAddress;

    /**
     * 商品名称
     */
    private String commodityName;

    /**
     * 单价
     */
    private String commodityPrice;

    /**
     * 商品数量
     */
    private String commodityNum;

    /**
     * 总价
     */
    private String commodityTotalPrice;

    /**
     * 版本号
     */
    private String version;

    /**
     * 用户token
     */
    private String token;

}
