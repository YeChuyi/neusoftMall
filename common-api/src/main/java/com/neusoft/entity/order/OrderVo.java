package com.neusoft.entity.order;

import com.neusoft.entity.common.Base.BaseEntity;
import com.neusoft.entity.common.Base.PageVo;
import com.neusoft.entity.goods.Commodity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @ClassName: OrderVo
 * @Description: 订单类
 * @Author: 林进华
 * @Date: 2019/4/21
 */
@NoArgsConstructor
@Data
@Accessors(chain = true)
@EqualsAndHashCode
public class OrderVo extends BaseEntity {

    private String orderId;//订单id
    private String orderNumber;//订单编号
    private String orderPrice;//订单金额
    private String customerId;//下单人id
    private String orderState;//订单状态 1待发货 2已发货 3已完成4已撤销
    private String orderDate;//订单日期
    private String orderAddress;//收货地址
    private String receiveTel;//联系方式
    private String receiveContact;//收件人
    private String orderRemark;//发货留言
    List<OrderDetailVO> commodityList;//订单明细信息
    private String token;//用户token
    private List<String> orderNumberList;//用户订单id列表
    private String customerName;//客户名字


}
