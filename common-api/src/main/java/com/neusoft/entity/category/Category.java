package com.neusoft.entity.category;

import com.neusoft.entity.common.Base.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @ClassName: Category
 * @Description: 商品类别类
 * @Author: 林进华
 * @Date: 2019/4/20
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class Category extends BaseEntity {

    private String categoryId;
    private String categoryParentId;
    private String categoryName;
    private String categoryLevel;
    private String categoryTreeCode;
    private String categoryRemark;
    private String token;

}
