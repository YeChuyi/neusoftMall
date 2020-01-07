package com.neusoft.entity.goods;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import com.neusoft.entity.common.Base.BasePageVo;
import net.bytebuddy.implementation.bind.annotation.Super;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * 部门：软件开发事业部
 * 描述：商品类视图对象
 * 作成者：yechuyi
 * 作成时间：2018/4/17
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain=true)//启用链式,如dept.setDname("yechuyi").setDb_source("test")
@EqualsAndHashCode
public class CommodityVO  extends BasePageVo implements Serializable {
    private String commodityCode; //商品编号
    private String commodityId;   //商品ID
    private String commodityName;//商品名称
    private String commodityColor;//商品颜色
    private String commodityFirstPicture;//商品首图
    private String commodityOriginalPrice;//商品原价
    private String commodityRetailPrice;//商品零售价
    private String commodityUnitId;//商品购买单位
    private String commodityIsSold;//商品是否上架，1是0否
    private String commodityInventory;//商品库存
    private String commodityIsLack;//商品是否缺货，1是0否
    private String commodityTotalCount;//商品销量
    private String commodityIsRecommend;//商品是否推荐，1是0否
    private String commodityDesc;//商品描述
    private String categoryFirst;//一级分类ID
    private String categorySecond;//二级分类ID
    private String token;

    //数据库不存在下列两字段
    private String categoryFirstName;//一级类目名称
    private String categorySecondName;//二级类目名称



    //规范要求
    private String createdBy;//创建人
    private String gmtCreate;//创建时间
    private String lastModifiedBy;//更新人
    private String gmtModified;//更新时间
    private int isDeleted;//是否作废1表示作废，0表示未作废
    private int sortNo;//序号
    private int version;//版本号

    //保存轮播图信息
    private List<CommodityPic> commodity_Pic_List;


}