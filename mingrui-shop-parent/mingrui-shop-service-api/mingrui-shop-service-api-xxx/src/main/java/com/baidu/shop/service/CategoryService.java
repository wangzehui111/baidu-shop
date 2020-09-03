package com.baidu.shop.service;

import com.alibaba.fastjson.JSONObject;
import com.baidu.shop.base.Result;
import com.baidu.shop.entity.CategoryEntity;
import com.baidu.shop.validate.group.MingruiOperation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName CategoryService
 * @Description: TODO
 * @Author wangzehui
 * @Date 2020/8/27
 * @Version V1.0
 **/
@Api(tags = "商品分类接口")
public interface CategoryService {

    @ApiOperation(value = "通过查询商品分类")
    @GetMapping(value = "category/list")
    public Result<List<CategoryEntity>> getCategoryByPid(Integer pid);

    @ApiOperation(value = "通过品牌id查询商品分类")
    @GetMapping(value = "category/getByBrand")
    public Result<List<CategoryEntity>> getByBrand(Integer brandId);

    @ApiOperation(value = "新增分类")
    @PostMapping(value = "category/add")
    Result<JSONObject> addCategory(@Validated({MingruiOperation.Add.class})
                                   @RequestBody CategoryEntity categoryEntity);

    @ApiOperation(value = "修改分类")
    @PutMapping(value = "category/edit")
    Result<JSONObject> editCategory(@Validated({MingruiOperation.Update.class})
                                    @RequestBody CategoryEntity categoryEntity);

    @ApiOperation(value = "删除分类")
    @DeleteMapping(value = "category/delete")
    Result<JSONObject> deleteCategory(Integer id);
}
