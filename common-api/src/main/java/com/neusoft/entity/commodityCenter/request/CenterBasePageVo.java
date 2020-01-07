package com.neusoft.entity.commodityCenter.request;

import com.neusoft.entity.common.Base.BasePageVo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @ClassName: CenterBasePage
 * @Description: 商品中心查询条件类
 * @Author: 林进华
 * @Date: 2019/4/20
 */
@NoArgsConstructor
@Data
@Accessors(chain = true)
@EqualsAndHashCode
@ApiModel("商品中心分页查询参数")
public class CenterBasePageVo extends BasePageVo {

    /**
     * 商品名称
     */
    private String commodityName;

    /**
     * 销量排序，1升序，2降序
     */
    private String sortingTotalCount;

    /**
     * 零售价排序，1升序，2降序
     */
    private String sortingRetailPrice;

    /**
     * 一级类目id
     */
    private String categoryFirst;

    /**
     * 二级类目id
     */
    private String categorySecond;

    /**
     * 用户token
     */
    private String token;

}
