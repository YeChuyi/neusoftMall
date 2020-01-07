package com.neusoft.receive.service;


import com.neusoft.entity.common.response.AppResponse;
import com.neusoft.entity.receive.Receive;
import com.neusoft.entity.receive.ReceiveVO;
import com.neusoft.entity.receive.Region;

import java.util.List;
/**
 * @ClassName ReceiveServiceimpl
 * @Description 收货地址管理接口
 * @Author yechuyi
 * @Date 2019/4/20
 */
public interface ReceiveService {

    /**
     * @Description 根据客户ID查询收获地址信息
     * @Author 叶楚义
     * @Date 2019/4/20
     * @param customerId 客户ID
     * @return com.neusoft.common.response.AppResponse
     */
    AppResponse getReceiveList(String customerId);

    /**
     * @Description 根据顾客ID来对其新增收货地址
     * @Author 叶楚义
     * @Date 2019/4/20
     * @param receive   收货地址信息
     * @return com.neusoft.common.response.AppResponse
     */
    AppResponse addReceive(Receive receive);

    /**
     * @Description 收货地址修改
     * @Author 叶楚义
     * @Date 2019/4/20
     * @param receive  收货地址信息
     * @return com.neusoft.common.response.AppResponse
     */
    AppResponse updateReceive(Receive receive);

    /**
     * @Description 删除收货地址
     * @Author 叶楚义
     * @Date 2019/4/20
     * @param receive 收货地址信息
     */
    void deleteReceive(Receive receive);

    /**
     * @Description 设为默认地址
     * @Author 叶楚义
     * @Date 2019/4/20
     * @param receive 收货地址信息
     * @return com.receive.common.response.AppResponse
     */
    AppResponse updateReceiveByDefault(Receive receive);

    /**
     * @Description 查询省市区
     * @Author 叶楚义
     * @Date 2019/4/20
     * @param region 地区信息
     * @return com.neusoft.common.response.AppResponse
     */
    AppResponse getRegion(Region region);

}
