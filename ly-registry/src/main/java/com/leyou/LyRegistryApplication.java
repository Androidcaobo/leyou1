package com.leyou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Author: Caobo
 * @Date: 2019/1/8
 * @Description: 注册中心启动类
 */
@SpringBootApplication
@EnableEurekaServer
public class LyRegistryApplication {
    public static void main(String[] args) {
        SpringApplication.run(LyRegistryApplication.class);
    }
}
