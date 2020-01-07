package com.neusoft.entity.collect;

import com.neusoft.entity.common.Base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @ClassName: Commodity
 * @Description: 收藏明细类
 * @Author: 林进华
 * @Date: 2019/4/21
 */
@NoArgsConstructor
@Data
@Accessors(chain = true)
@EqualsAndHashCode
public class CollectDetailVO {

    /**
     * 商品id
     */
    private String commodityId;

    /**
     * 客户id
     */
    private String customerId;

    /**
     * 商品名字
     */
    private String commodityName;

    /**
     * 商品首图地址
     */
    private String commodityFirstPicture;

    /**
     * 商品单价
     */
    private String commodityRetailPrice;

    /**
     * 用户token
     */
    private String token;

}
