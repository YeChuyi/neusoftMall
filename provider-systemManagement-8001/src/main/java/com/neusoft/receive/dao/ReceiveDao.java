package com.neusoft.receive.dao;



import com.neusoft.entity.receive.Receive;
import com.neusoft.entity.receive.Region;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
/**
 * @ClassName ReceiveServiceimpl
 * @Description 收货地址管理DAO层
 * @Author yechuyi
 * @Date 2019/4/20
 */
@Mapper
public interface ReceiveDao {

    /**
     * @Description 根据客户ID查询收获地址信息
     * @Author 叶楚义
     * @Date 2019/4/20
     * @param customerId 客户ID
     * @return com.neusoft.common.response.AppResponse
     */
    List<Receive> getReceiveList(String  customerId);

    /**
     * @Description 根据顾客ID来对其新增收货地址
     * @Author 叶楚义
     * @Date 2019/4/20
     * @param receive   收货地址信息
     * @return com.neusoft.common.response.AppResponse
     */
    int addReceive(Receive receive);


    /**
     * @Description 收货地址修改
     * @Author 叶楚义
     * @Date 2019/4/20
     * @param receive  收货地址信息
     * @return Integer
     */
    int updateReceive(Receive receive);

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
     * @return Integer
     */
    int updateReceiveByDefault(Receive receive);

    /**
     * @Description 查询省市区
     * @Author 叶楚义
     * @Date 2019/4/20
     * @param region 地区信息
     * @return java.util.ArrayList
     */
    List<Region> getRegion(Region  region);
}
