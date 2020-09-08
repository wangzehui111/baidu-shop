package com.baidu.shop.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baidu.shop.base.BaseApiService;
import com.baidu.shop.base.Result;
import com.baidu.shop.entity.CategoryBrandEntity;
import com.baidu.shop.entity.CategoryEntity;
import com.baidu.shop.entity.SpecParamEntity;
import com.baidu.shop.mapper.BrandMapper;
import com.baidu.shop.mapper.CategoryBrandMapper;
import com.baidu.shop.mapper.CategoryMapper;
import com.baidu.shop.mapper.SpecParamMapper;
import com.baidu.shop.service.BrandService;
import com.baidu.shop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName CategoryServiceImpl
 * @Description: TODO
 * @Author wangzehui
 * @Date 2020/8/27
 * @Version V1.0
 **/
@RestController
public class CategoryServiceImpl extends BaseApiService implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Resource
    private CategoryBrandMapper categoryBrandMapper;

    @Resource
    private SpecParamMapper specParamMapper;

    @Override
    public Result<List<CategoryEntity>> getCategoryByPid(Integer pid) {

        CategoryEntity categoryEntity = new CategoryEntity();

        categoryEntity.setParentId(pid);

        List<CategoryEntity> list = categoryMapper.select(categoryEntity);

        return this.setResultSuccess(list);
    }

    @Override
    public Result<List<CategoryEntity>> getByBrand(Integer brandId) {

        List<CategoryEntity> byBrandId =  categoryMapper.getByBrandId(brandId);

        return this.setResultSuccess(byBrandId);
    }

    @Transactional
    @Override
    public Result<JSONObject> addCategory(CategoryEntity categoryEntity) {
        //通过页面传递过来的parentid查询parentid对应的数据是否为父节点isParent == 1
        //如果parentid != 1 需要将其修改为1

        //通过新增节点的父id,将父节点的parent状态改为1
        CategoryEntity parentCateEntity = new CategoryEntity();
        parentCateEntity.setId(categoryEntity.getParentId());
        parentCateEntity.setIsParent(1);

        categoryMapper.updateByPrimaryKeySelective(parentCateEntity);
        categoryMapper.insertSelective(categoryEntity);
        return this.setResultSuccess();
    }

    @Transactional
    @Override
    public Result<JSONObject> editCategory(CategoryEntity categoryEntity) {

        categoryMapper.updateByPrimaryKeySelective(categoryEntity);
        return this.setResultSuccess();
    }

    @Transactional
    @Override
    public Result<JSONObject> deleteCategory(Integer id) {

        //验证传入id是否有效 并且查询出来的数据对接下来的数据有用
        CategoryEntity categoryEntity = categoryMapper.selectByPrimaryKey(id);
        if (categoryEntity == null) {
            return this.setResultError("当前信息不存在");
        }
        //判断当前节点是否为父节点
        if(categoryEntity.getParentId() == 1){
            return this.setResultError("当前节点为父节点,不能删除");
        }

        Example example1 = new Example(CategoryBrandEntity.class);
        example1.createCriteria().andEqualTo("categoryId",id);
        List<CategoryBrandEntity> list1 = categoryBrandMapper.selectByExample(example1);

        if (list1.size() >= 1) return this.setResultError("当前分类绑定了品牌,不能被删除");

        Example example2 = new Example(SpecParamEntity.class);
        example2.createCriteria().andEqualTo("cid",id);
        List<SpecParamEntity> list2 = specParamMapper.selectByExample(example2);

        if (list2.size() >= 1) return this.setResultError("当前根类绑定了规格,不能删除");

        //构建条件查询 通过当前被删除节点的parentid查询数据
        Example example = new Example(CategoryEntity.class);
        example.createCriteria().andEqualTo("parentId",categoryEntity.getParentId());
        List<CategoryEntity> list = categoryMapper.selectByExample(example);
        //若只查询出一条数据
        if(list.size() == 1 ){//将父节点的isParent状态改为0

            CategoryEntity parentCateEntity = new CategoryEntity();
            parentCateEntity.setId(categoryEntity.getParentId());
            parentCateEntity.setIsParent(0);
            categoryMapper.updateByPrimaryKeySelective(parentCateEntity);
        }

        categoryMapper.deleteByPrimaryKey(id);
        return this.setResultSuccess();
    }
}
