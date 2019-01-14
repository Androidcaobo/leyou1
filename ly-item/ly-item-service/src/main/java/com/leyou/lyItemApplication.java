package com.leyou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Author: Caobo
 * @Date: 2019/1/8
 * @Description: 商品服务启动类
 */
@MapperScan("com.leyou.item.mapper")
@SpringBootApplication
@EnableDiscoveryClient
public class lyItemApplication {
    public static void main(String[] args) {
        SpringApplication.run(lyItemApplication.class);
    }
}
