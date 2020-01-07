package com.neusoft.entity.commodityCenter;

import com.neusoft.entity.goods.CommodityPicVo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @ClassName: Commodity
 * @Description: 商品类
 * @Author: 林进华
 * @Date: 2019/4/21
 */
@NoArgsConstructor
@Data
@Accessors(chain = true)
@EqualsAndHashCode
public class CommodityCenterDetailVO {

    /**
     * 商品ID
     */
    private String commodityId;

    /**
     * 客户id
     */
    private String customerId;

    /**
     * 商品编号
     */
    private String commodityCode;

    /**
     * 商品名称
     */
    private String commodityName;
    /**
     * 颜色
     */
    private String commodityColor;

    /**
     * 商品首图
     */
    private String pictureAddress;

    /**
     * 原价
     */
    private String commodityOriginalPrice;

    /**
     * 零售价
     */
    private String commodityRetailPrice;

    /**
     * 购买单位
     */
    private String commodityUnitId;

    /**
     * 购买单位名称
     */
    private String commodityUnitName;

    /**
     * 是否缺货1是0否
     */
    private String commodityIsLack;

    /**
     * 是否收藏0收藏1未收藏
     */
    private String isCollect;

    /**
     * 销量
     */
    private String commodityTotalCount;

    /**
     * 版本号
     */
    private String version;

    /**
     * 商品图片信息
     */
    List<CommodityPicVo> pictureList;

    /**
     * 用户token
     */
    private String token;

}
