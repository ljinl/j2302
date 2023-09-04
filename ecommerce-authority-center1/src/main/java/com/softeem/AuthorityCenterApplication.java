package com.softeem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.softeem.mapper")
public class AuthorityCenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthorityCenterApplication.class,args);
    }
}
