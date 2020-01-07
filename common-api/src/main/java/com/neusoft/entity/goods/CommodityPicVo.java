package com.neusoft.entity.goods;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

import java.io.Serializable;


/**
 * 部门：软件开发事业部
 * 描述：轮播图实体类视图对象
 * 作成者：yechuyi
 * 作成时间：2018/4/17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain=true)//启用链式,如dept.setDname("yechuyi").setDb_source("test")
@EqualsAndHashCode
public class CommodityPicVo implements Serializable {
    private String pictureId;//图片ID
    private String commodityId;//商品ID
    private String pictureIsFistPicture ;//是否主页，1是0否
    private String pictureAddress;//轮播图地址


}
