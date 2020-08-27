package com.baidu;

import io.micrometer.core.instrument.util.JsonUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @ClassName RunEurekaServerApplication
 * @Description: TODO
 * @Author wangzehui
 * @Date 2020/8/27
 * @Version V1.0
 **/
@SpringBootApplication
@EnableEurekaServer
public class RunEurekaServerApplication {

    public static void main(String[] args) {
        System.out.println("111");
        SpringApplication.run(RunEurekaServerApplication.class);
    }
}
