package com.baidu.shop.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baidu.shop.base.BaseApiService;
import com.baidu.shop.base.Result;
import com.baidu.shop.dto.SpecGroupDTO;
import com.baidu.shop.dto.SpecParamDTO;
import com.baidu.shop.entity.SpecGroupEntity;
import com.baidu.shop.entity.SpecParamEntity;
import com.baidu.shop.mapper.SpecGroupMapper;
import com.baidu.shop.mapper.SpecParamMapper;
import com.baidu.shop.service.SpecGroupService;
import com.baidu.shop.utils.BaiduBeanUtil;
import com.baidu.shop.utils.ObjectUtil;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName SpecGroupServiceImpl
 * @Description: TODO
 * @Author wangzehui
 * @Date 2020/9/3
 * @Version V1.0
 **/

@RestController
public class SpecGroupServiceImpl extends BaseApiService implements SpecGroupService {

    @Resource
    private SpecGroupMapper specGroupMapper;

    @Resource
    private SpecParamMapper specParamMapper;

    @Override
    public Result<List<SpecGroupEntity>> getSpecGroupInfo(SpecGroupDTO specGroupDTO) {

        Example example = new Example(SpecGroupEntity.class);

        if(ObjectUtil.isNotNull(specGroupDTO.getCid()))
            example.createCriteria().andEqualTo("cid",specGroupDTO.getCid());

        List<SpecGroupEntity> list = specGroupMapper.selectByExample(example);
        return setResultSuccess(list);
    }

    @Transactional
    @Override
    public Result<JSONObject> add(SpecGroupDTO specGroupDTO) {

        specGroupMapper.insertSelective(BaiduBeanUtil.copyProperties
                (specGroupDTO,SpecGroupEntity.class));

        return this.setResultSuccess();
    }

    @Transactional
    @Override
    public Result<JSONObject> edit(SpecGroupDTO specGroupDTO) {

        specGroupMapper.updateByPrimaryKeySelective(BaiduBeanUtil.copyProperties
                (specGroupDTO,SpecGroupEntity.class));

        return this.setResultSuccess();
    }

    @Transactional
    @Override
    public Result<JSONObject> delete(Integer id) {

        specGroupMapper.deleteByPrimaryKey(id);

        return this.setResultSuccess();
    }

    @Override
    public Result<List<SpecParamEntity>> getSpecParamInfo(SpecParamDTO specParamDTO) {

        if(ObjectUtil.isNull(specParamDTO.getGroupId()))  return this.setResultError("规格id不能为空");

        Example example = new Example(SpecParamEntity.class);
        example.createCriteria().andEqualTo("groupId",specParamDTO.getGroupId());

        List<SpecParamEntity> list = specParamMapper.selectByExample(example);

        return this.setResultSuccess(list);
    }

    @Transactional
    @Override
    public Result<JSONObject> addParam(SpecParamDTO specParamDTO) {

        specParamMapper.insertSelective(BaiduBeanUtil.copyProperties(specParamDTO,SpecParamEntity.class));

        return this.setResultSuccess();
    }

    @Transactional
    @Override
    public Result<JSONObject> editParam(SpecParamDTO specParamDTO) {

        specParamMapper.updateByPrimaryKeySelective(BaiduBeanUtil.copyProperties(specParamDTO,SpecParamEntity.class));

        return this.setResultSuccess();
    }

    @Override
    public Result<JSONObject> delParam(Integer id) {

        specParamMapper.deleteByPrimaryKey(id);

        return this.setResultSuccess();
    }


}
