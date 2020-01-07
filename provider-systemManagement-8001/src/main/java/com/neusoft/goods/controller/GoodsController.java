package com.neusoft.goods.controller;
import com.neusoft.config.RedisUtils;
import com.neusoft.entity.common.response.AppResponse;
import com.neusoft.entity.goods.CommodityVO;
import com.neusoft.entity.goods.Commodity;
import com.neusoft.fastdfs.FastDfsService;
import com.neusoft.goods.service.GoodsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName GoodsController
 * @Description 商品管理
 * @Author 叶楚义
 * @Date 2019/4/18
 */

@RestController
@RequestMapping("mall/backend/goods")
@Slf4j
@Api("商品管理")
public class GoodsController {
    @Autowired
    private RedisUtils redisUtils;

    @Resource
    private GoodsService goodsService;   //注入GoodsService

    @Resource
    private FastDfsService fastDfsService;//引入上传文件Service

    /**
     * @Description 商品列表
     * @Author 叶楚义
     * @Date 2019/4/19
     * @param commodityVO 商品信息
     * @return com.neusoft.common.response.AppResponse
     */
    @ApiOperation(value ="商品列表")
    @RequestMapping(value = "getGoodsList",method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    public AppResponse listUsers(CommodityVO commodityVO) throws Exception {
        try {
            Commodity commodity =new Commodity();
            BeanUtils.copyProperties(commodityVO, commodity);
            //根据条件查询相关商品信息，返回map对象。
            return goodsService.listGoods(commodityVO);
        } catch (Exception e) {
            log.error("商品获取异常", e);
            throw new Exception("查询错误，请重试");
        }
    }



    /**
     * @Description 商品上架/下架
     * @Author 叶楚义
     * @Date 2019/4/20
     * @param commodityVO 商品信息
     * @return com.neusoft.common.response.AppResponse
     */
    @ApiOperation(value ="商品上架/下架")
    @RequestMapping(value = "updateGoodsIsSell",method = RequestMethod.PUT, produces = {"application/json;charset=UTF-8"})
    public AppResponse updateGoodsIsSell(@Valid CommodityVO commodityVO) throws Exception {
        try {
            //调用redis获取后台登录用户信息
//            String userId = redisUtils.get(commodityVO.getToken()+"_id");
//            System.out.println(commodityVO.getToken());
//            commodityVO.setLastModifiedBy(userId);
            commodityVO.setLastModifiedBy("1");
            Commodity commodity =new Commodity();
            BeanUtils.copyProperties(commodityVO, commodity);
            return goodsService.updateGoodsIsSell(commodity);
        } catch (Exception e) {
            log.error("商品上架/下架信息错误", e);
            throw new Exception("系统错误，请重试");
        }
    }



    /**
     * @Description 根据commodityId查询商品信息
     * @Author 叶楚义
     * @Date 2019/4/20
     * @param commodityVO 商品信息
     * @return com.neusoft.common.response.AppResponse
     */
    @ApiOperation(value ="商品详情")
    @RequestMapping(value = "getGoodsDetail",method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    public AppResponse getGoodsDetail(@Valid CommodityVO commodityVO) throws Exception {
        Commodity commodity ;
        try {
            commodity = goodsService.getGoodsDetail(commodityVO);
        } catch (Exception e) {
            log.error("商品查询错误", e);
            throw new Exception("查询错误，请重试");
        }
        //判断商品是否为空，若为空，返回状态码4
        if (commodity == null) {
            return AppResponse.notFound("无查询结果");
        }
        return AppResponse.success("查询成功", commodity);
    }



    /**
     * @Description 商品删除
     * @Author 叶楚义
     * @Date 2019/4/20
     * @param commodityVO 商品信息
     * @return com.neusoft.common.response.AppResponse
     */
    @ApiOperation(value ="商品删除")
    @RequestMapping(value = "deleteGoods",method = RequestMethod.PUT, produces = {"application/json;charset=UTF-8"})
    public AppResponse deleteGoods(@Valid CommodityVO commodityVO) {
        try {
            //调用redis获取后台登录用户信息
            String userId = redisUtils.get(commodityVO.getToken()+"_id");
            commodityVO.setLastModifiedBy(userId);

            Commodity commodity = new Commodity();
            BeanUtils.copyProperties(commodityVO, commodity);
            goodsService.deleteGoods(commodity);
            return AppResponse.success("商品删除成功");
        } catch (Exception e) {
            log.error("商品删除错误", e);
            return AppResponse.notFound("删除失败");
        }
    }

    /**
     * 功能：商品修改
     * 作成者：cqluo
     * 作成时间：2019/4/23
     */
    @ApiOperation(value ="商品修改")
    @RequestMapping(value = "updateGoods",method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public AppResponse updateGoods(@Valid CommodityVO commodityVO) throws Exception{
        try {
            String userId = redisUtils.get(commodityVO.getToken()+"_id");
            commodityVO.setLastModifiedBy(userId);

            Commodity commodity =new Commodity();
            BeanUtils.copyProperties(commodityVO, commodity);
            return goodsService.updateGoods(commodity);
        } catch (Exception e) {
            log.error("商品信息修改错误", e);
            throw new Exception("系统错误，请重试");
        }
    }

    /**
     * 功能：商品新增
     * 作成者：cqluo
     * 作成时间：2019/4/23
     */
    @ApiOperation(value ="商品新增")
    @RequestMapping(value = "addGoods",method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public AppResponse addGoods(@Valid CommodityVO commodityVO) throws Exception{
        try {
            String userId = redisUtils.get(commodityVO.getToken()+"_id");
            commodityVO.setLastModifiedBy(userId);
            commodityVO.setCreatedBy(userId);
            Commodity commodity =new Commodity();
            BeanUtils.copyProperties(commodityVO, commodity);
            return goodsService.addGoods(commodity);
        } catch (Exception e) {
            log.error("商品信息修改错误", e);
            throw new Exception("系统错误，请重试");
        }
    }

    @ApiOperation(value = "上传图片")
    @PostMapping(value = "uploadFile",produces = {"application/json;charset=UTF-8"})
    public  AppResponse uploadFile(@RequestParam("file") MultipartFile[] file) throws Exception{
        try{
            System.out.println(file);
            Map<String, Object> param = new HashMap<>();
            param.put("files", file);
            Map<String, String> resultParam = fastDfsService.uploadFile(param);
            System.out.println(resultParam.values());
            return AppResponse.success("上传成功",resultParam);
        }catch (Exception e){
            log.error("上传图片错误", e);
            throw new Exception("系统错误，请重试");
        }
    }

}
