package com.neusoft.entity.common.Base;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;
@SuppressWarnings("unchecked")
@ApiModel(description = "分页")
@Data
public class PageVo<T> {

    private List<T> list;

    private Integer totalRecords;


}
