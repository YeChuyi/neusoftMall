package com.neusoft.receive.service.impl;


import com.neusoft.config.RedisUtils;


import com.neusoft.entity.common.response.AppResponse;

import com.neusoft.entity.receive.Receive;
import com.neusoft.entity.receive.Region;
import com.neusoft.receive.dao.ReceiveDao;
import com.neusoft.receive.service.ReceiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName ReceiveServiceimpl
 * @Description 收货地址管理接口实现类
 * @Author yechuyi
 * @Date 2019/4/20
 */
@Transactional
@Service
public class ReceiveServiceImpl implements ReceiveService {

    @Resource
    private ReceiveDao receiveDao;

    @Autowired
    private RedisUtils redisUtils;

    /**
     * @Description 根据客户ID查询收获地址信息
     * @Author 叶楚义
     * @Date 2019/4/20
     * @param customerId 客户ID
     * @return com.neusoft.common.response.AppResponse
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public AppResponse getReceiveList(String customerId) {
        //查询收获地址
        List<Receive> ReceiveList = receiveDao.getReceiveList(customerId);
        //判断所获取数组是否为空，若为空，返回状态码4，并标注查询无结果。
        if(ReceiveList.isEmpty()){
            return AppResponse. notFound("查询无结果!");
        }
        else {
            return AppResponse.success("查询成功！", ReceiveList);
        }
    }

    /**
     * @Description 根据顾客ID来对其新增收货地址
     * @Author 叶楚义
     * @Date 2019/4/20
     * @param receive   收货地址信息
     * @return com.neusoft.common.response.AppResponse
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public AppResponse addReceive(Receive receive) {
        AppResponse appResponse = AppResponse.success("新增成功！");
        int count=receiveDao.addReceive(receive);
        //判断count数值，若为0，则新增失败。
        if(0==count){
            appResponse = AppResponse.bizError("新增失败，请重试！");
        }
        return appResponse;
    }

    /**
     * @Description 收货地址修改
     * @Author 叶楚义
     * @Date 2019/4/20
     * @param receive  收货地址信息
     * @return com.neusoft.common.response.AppResponse
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public AppResponse updateReceive(Receive receive) {
        AppResponse appResponse = AppResponse.success("修改成功！");
        int count = receiveDao.updateReceive(receive);
        //判断count数值，若为0，则修改失败。
        if(0 == count) {
            appResponse = AppResponse.bizError("修改失败，请重试！");
        }
        return appResponse;
    }

    /**
     * @Description 删除收货地址
     * @Author 叶楚义
     * @Date 2019/4/20
     * @param receive 收货地址信息
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteReceive(Receive receive) {

        // 删除收货地址
        receiveDao.deleteReceive(receive);


    }

    /**
     * @Description 设为默认地址
     * @Author 叶楚义
     * @Date 2019/4/20
     * @param receive 收货地址信息
     * @return com.receive.common.response.AppResponse
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public AppResponse updateReceiveByDefault(Receive receive) {
        AppResponse appResponse = AppResponse.success("修改成功！");
        int count = receiveDao.updateReceiveByDefault(receive);
        //判断count数值，若为0，则修改失败。
        if(0 == count) {
            appResponse = AppResponse.bizError("修改失败，请重试！");
        }
        return appResponse;
    }


    /**
     * @Description 查询省市区
     * @Author 叶楚义
     * @Date 2019/4/20
     * @param region 地区信息
     * @return com.neusoft.common.response.AppResponse
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public AppResponse getRegion(Region region){
        List<Region> regionList =receiveDao.getRegion(region);
        //判断所获取数组是否为空，若为空，返回状态码4，并标注查询无结果。
        if(regionList.isEmpty()){
            return AppResponse.notFound("查询无结果");
        }
        return AppResponse.success("查询成功！", regionList);
    }
}
