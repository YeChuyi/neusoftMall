package com.neusoft.entity.order;

import com.neusoft.entity.common.Base.BaseEntity;
import com.neusoft.entity.goods.Commodity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @ClassName: Order
 * @Description: 订单类
 * @Author: 蔡程健
 * @Date: 2019/4/21
 */
@NoArgsConstructor
@Data
@Accessors(chain = true)
@EqualsAndHashCode
public class Order extends BaseEntity {

    private String orderId;//订单id
    private String orderNumber;//订单编号
    private String orderPrice;//订单金额
    private String customerId;//下单人
    private String orderState;//订单状态 1待发货 2已发货 3已完成4已撤销
    private String orderDate;//订单日期
    private String orderAddress;//收货地址
    private String receiveTel;//联系方式
    private String receiveContact;//收件人
    private String orderRemark;//发货留言
    private List<Commodity> commodityList;//商品信息
    private List<OrderDetail> orderDetailList;//订单明细信息
    private String token;//用户token

}
