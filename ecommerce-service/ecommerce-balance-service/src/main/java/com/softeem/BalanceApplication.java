package com.softeem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@MapperScan("com.softeem.mapper")
@EnableDiscoveryClient
public class BalanceApplication {
    public static void main(String[] args) {
        SpringApplication.run(BalanceApplication.class,args);
    }
}