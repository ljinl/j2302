package com.softeem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@MapperScan("com.softeem.mapper")
@EnableDiscoveryClient          ///当前
public class GoodsApplication_8085 {
    public static void main(String[] args) {
        SpringApplication.run(GoodsApplication_8085.class,args);
    }
}