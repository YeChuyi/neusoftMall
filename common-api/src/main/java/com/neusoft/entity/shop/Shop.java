package com.neusoft.entity.shop;

import com.neusoft.entity.common.Base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
@Accessors(chain = true)
@Data
@EqualsAndHashCode
public class Shop extends BaseEntity {

    /**
     * 购物车id
     */
    private String shopId;
    /**
     * 商品id
     */
    private String commodityId;
    /**
     * 商品数量
     */
    private String shopNumber;
    /**
     * 客户id
     */
    private String customerId;
    /**
     * 用户token
     */
    private String token;

}
