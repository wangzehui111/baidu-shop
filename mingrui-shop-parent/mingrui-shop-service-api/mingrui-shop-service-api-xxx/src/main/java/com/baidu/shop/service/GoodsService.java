package com.baidu.shop.service;

import com.alibaba.fastjson.JSONObject;
import com.baidu.shop.base.Result;
import com.baidu.shop.dto.SkuDTO;
import com.baidu.shop.dto.SpuDTO;
import com.baidu.shop.entity.SpuDetailEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName GoodsService
 * @Description: TODO
 * @Author wangzehui
 * @Date 2020/9/7
 * @Version V1.0
 **/
@Api(value = "商品接口")
public interface GoodsService {

    @ApiOperation(value = "获取spu信息")
    @GetMapping(value = "goods/getSpuInfo")
    Result<List<SpuDTO>> getSpuInfo(@SpringQueryMap SpuDTO spuDTO);

    @ApiOperation(value = "新建商品")
    @PostMapping(value = "goods/add")
    Result<JSONObject> saveGoods(@RequestBody SpuDTO spuDTO);

    @ApiOperation(value = "通过spuid查询detail信息")
    @GetMapping(value = "goods/getDetailBySpuId")
    Result<SpuDetailEntity> getDetailBySpuId(@RequestParam Integer spuId);

    @ApiOperation(value = "通过spuid查询sku")
    @GetMapping(value = "goods/getSkuBySpuId")
    Result<List<SkuDTO>> getSkuBySpuId(@RequestParam Integer spuId);

    @ApiOperation(value = "修改商品")
    @PutMapping(value = "goods/add")
    Result<JSONObject> editGoods(@RequestBody SpuDTO spuDTO);

    @ApiOperation(value = "删除商品" )
    @DeleteMapping(value = "goods/delGoods")
    Result<JSONObject> delGoods(Integer spuId);

    @ApiOperation(value = "商品上下架")
    @GetMapping(value = "goods/getSaleable")
    Result<JSONObject> getSaleable(SpuDTO spuDTO);

}


