package com.neusoft.entity.collect;

import com.neusoft.entity.common.Base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @ClassName: Commodity
 * @Description: 收藏实体类
 * @Author: 林进华
 * @Date: 2019/4/21
 */
@NoArgsConstructor
@Data
@Accessors(chain = true)
@EqualsAndHashCode
public class Collect extends BaseEntity {

    /**
     * 收藏id
     */
    private String collectId;

    /**
     * 客户id
     */
    private String customerId;

    /**
     * 商品id
     */
    private String commodityId;

    /**
     * 是否收藏1是0否
     */
    private String collectFlag;

    /**
     * 用户token
     */
    private String token;

}
