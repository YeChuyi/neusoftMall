package com.neusoft.entity.order;

import com.neusoft.entity.common.Base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @ClassName: OrderDetail
 * @Description: 订单明细类
 * @Author: 林进华
 * @Date: 2019/4/21
 */
@NoArgsConstructor
@Data
@Accessors(chain = true)
@EqualsAndHashCode
public class OrderDetail extends BaseEntity {

    private String orderDetailId;//订单明细id
    private String orderId;//订单id
    private String commodityId;//商品id
    private String commodityPrice;//单价
    private String commodityNum;//数量
    private String commodityTotalPrice;//总价
    private String token;//用户token

}
