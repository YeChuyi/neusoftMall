package com.neusoft.goods.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.config.RedisUtils;
import com.neusoft.entity.common.Base.PageVo;
import com.neusoft.entity.common.response.AppResponse;
import com.neusoft.entity.goods.Commodity;
import com.neusoft.entity.goods.CommodityPic;
import com.neusoft.entity.goods.CommodityVO;
import com.neusoft.entity.user.UserInfo;
import com.neusoft.entity.user.UserInfoVO;
import com.neusoft.goods.dao.GoodsDao;
import com.neusoft.goods.service.GoodsService;
import com.neusoft.user.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName GoodsServiceImpl
 * @Description 商品管理接口实现类
 * @Author yechuyi
 * @Date 2019/4/18
 */
@Transactional
@Service
public class GoodsServiceImpl implements GoodsService {
    @Resource
    private GoodsDao goodsDao;

    @Autowired
    private RedisUtils redisUtils;

    /**
     * @Description 商品列表
     * @Author 叶楚义
     * @Date 2019/4/19
     * @param commodityVO 商品信息
     * @return com.neusoft.common.response.AppResponse
     */
    @Override
    public AppResponse listGoods(CommodityVO commodityVO) {
        //根据条件查询商品列表
        List<Commodity> goodsInfoList = goodsDao.listGoods(commodityVO);
        //判断所获取的商品数组是否为空
        if(goodsInfoList.isEmpty()){
            return AppResponse.notFound("查询无结果！");
        }else {
            //实例化分页对象
            PageVo pageVo = new PageVo();
            //设置分页参数
            PageHelper.startPage(commodityVO.getPageNum(), commodityVO.getPageSize());
            //执行分页
            pageVo.setList(goodsInfoList);
            //设置总共记录数
            pageVo.setTotalRecords((int) new PageInfo<Commodity>(goodsInfoList).getTotal());
            return AppResponse.success("查询成功！", pageVo);
        }
    }


    /**
     * @Description 商品上架/下架
     * @Author 叶楚义
     * @Date 2019/4/20
     * @param commodity 商品信息
     * @return com.neusoft.common.response.AppResponse
     */
    @Override
    public AppResponse updateGoodsIsSell(Commodity commodity){
        AppResponse appResponse = AppResponse.success("商品上架/下架成功！");
        //传递commodity参数于Dao中，修改数据库
        int count = goodsDao.updateGoodsIsSell(commodity);
        if(0 == count) {
            appResponse = AppResponse.bizError("商品上架/下架失败，请重试！");
        }
        return appResponse;
    }

    /**
     * @Description：根据commodityId查询商品信息
     * @Author：叶楚义
     * @Date：2019/4/20
     * @param：commodityId （String 商品Id），（必选）
     * @return：com.neusoft.entity.goods.Commodity;
     */
    @Override
    public Commodity getGoodsDetail(CommodityVO commodityVO) {
        return goodsDao.getGoodsDetail(commodityVO);

    }

    /**
     * @Description:商品删除
     * @Author：叶楚义
     * @Date: 2019/4/20
     * @param：commodityId （String 商品Id），（必选）
     * @return：void
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteGoods(Commodity commodity) {
        // 删除商品
        goodsDao.deleteGoods(commodity);
    }

    /**
     * 功能：商品修改
     * 作成者：cqluo
     * 作成时间：2019/4/23
     */
    @Override
    public AppResponse updateGoods(Commodity commodity) {
        List<CommodityPic> Piclis = commodity.getPictureList();//获得前台传过来的图片数组
        int count1 = goodsDao.updateGoods(commodity);//count1用于判断商品（除了商品中的图片）是否修改成功
        if(0 == count1) {
            return AppResponse.bizError("修改失败，请重试！");
        }

        return AppResponse.success("修改成功！");
    }

    /**
     * 功能：商品新增
     * 作成者：cqluo
     * 作成时间：2019/4/23
     */
    @Override
    public AppResponse addGoods(Commodity commodity) {
        List<CommodityPic> Piclis = commodity.getPictureList();//获得前台传过来的图片数组
        int count1 = goodsDao.addGoods(commodity);//count1用于判断商品（除了商品中的图片）是否添加成功
        if(0 == count1) {
            return AppResponse.bizError("新增失败，请重试！");
        }
        return AppResponse.success("新增成功！");
    }
}
