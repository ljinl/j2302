package com.softeem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author LJL
 * @Date 2023:08:23:11:34
 **/
@SpringBootApplication
@MapperScan("com.softeem.mapper")
@EnableDiscoveryClient
public class AuthorityCenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthorityCenterApplication.class,args);
    }
}