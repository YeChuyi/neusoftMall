package com.neusoft.entity.commodityCenter.request;

import com.neusoft.entity.common.Base.BasePageVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel("商品中心分页参数")
public class CenterBasePage extends BasePageVo {

    @ApiModelProperty(value = "商品名称")
    private String commodityName;

    @ApiModelProperty(value = "销量排序，1升序，2降序")
    private String sortingTotalCount;

    @ApiModelProperty(value = "零售价排序，1升序，2降序")
    private String sortingRetailPrice;

    @ApiModelProperty(value = "一级类目id")
    private String categoryFirst;

    @ApiModelProperty(value = "二级类目id")
    private String categorySecond;

}
