package com.baidu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @ClassName RunUploadServerAppliaction
 * @Description: TODO
 * @Author wangzehui
 * @Date 2020/9/1
 * @Version V1.0
 **/
@SpringBootApplication
@EnableEurekaClient
public class RunUploadServerAppliaction {

    public static void main(String[] args) {
        SpringApplication.run(RunUploadServerAppliaction.class);
    }
}
