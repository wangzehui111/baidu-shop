package com.baidu.shop.service;

import com.baidu.shop.base.Result;
import com.baidu.shop.dto.BrandDTO;
import com.baidu.shop.entity.BrandEntity;
import com.baidu.shop.validate.group.MingruiOperation;
import com.github.pagehelper.PageInfo;
import com.google.gson.JsonObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName BrandService
 * @Description: TODO
 * @Author wangzehui
 * @Date 2020/8/31
 * @Version V1.0
 **/
@Api(value = "品牌接口")
public interface BrandService {

    @GetMapping(value = "brand/getBrandInfo")
    @ApiOperation(value = "查询品牌信息")
    public Result<PageInfo<BrandEntity>> getPageInfo(BrandDTO brandDTO);

    @PostMapping(value = "brand/saveBrand")
    @ApiOperation(value = "新增品牌信息")
    public Result<JsonObject> saveBrand(@Validated(MingruiOperation.Add.class) @RequestBody BrandDTO brandDTO);

    @PutMapping(value = "brand/saveBrand")
    @ApiOperation(value = "修改品牌信息")
    public Result<JsonObject> editBrand(@Validated(MingruiOperation.Update.class) @RequestBody BrandDTO brandDTO);

    @DeleteMapping(value = "brand/delete")
    @ApiOperation(value = "删除品牌信息")
    public Result<JsonObject> deleteBrand(Integer id);
}
