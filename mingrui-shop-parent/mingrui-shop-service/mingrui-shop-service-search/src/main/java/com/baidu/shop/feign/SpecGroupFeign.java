package com.baidu.shop.feign;

import com.baidu.shop.service.SpecGroupService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @ClassName SpecGroupFeign
 * @Description: TODO
 * @Author wangzehui
 * @Date 2020/9/17
 * @Version V1.0
 **/
@FeignClient(contextId = "SpecGroupService", value = "xxx-service")
public interface SpecGroupFeign extends SpecGroupService {
}
