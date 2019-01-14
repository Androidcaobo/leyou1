package com.leyou;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @Author: Caobo
 * @Date: 2019/1/8
 * @Description: 网关启动类
 */
@SpringCloudApplication //熔断 注册中心 springBootApplication
@EnableZuulProxy
public class LyGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(LyGatewayApplication.class);
    }
}
