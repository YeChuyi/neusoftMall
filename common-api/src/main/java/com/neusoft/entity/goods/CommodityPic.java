package com.neusoft.entity.goods;

import com.neusoft.entity.common.Base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;


/**
 * 部门：软件开发事业部
 * 描述：略
 * 作成者：yechuyi
 * 作成时间：2018/4/17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain=true)//启用链式,如dept.setDname("yechuyi").setDb_source("test")
public class CommodityPic extends BaseEntity implements Serializable {
    private int pictureId;//图片ID
    private String commodityId; //商品ID
    private int pictureIsFistPicture ;//是否主页，1是0否
    private String pictureAddress;//轮播图地址
    private String token;
}
