package com.softeem.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;

/**
 * @Author LJL
 * @Date 2023:08:23:14:38
 **/

// WebFlux---> 响应式web编程 ---》 异步非阻塞IO

// 局部过滤器，部署给单独的路由生效

@Component
public class MyOAuthGatewayFilterFactory extends AbstractGatewayFilterFactory {
    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            //验证请求头中是否携带了token
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();
            String token = request.getHeaders().getFirst("token");
            if ("softeem".equals(token)) {
                // 继续运行  webFlux Mono ---》 Object
                //  Mono.just("番茄炒鸡蛋")    String s="番茄炒鸡蛋";
                ///                Flux-----> Array List
                return chain.filter(exchange);
            }
            // 设置响应编码
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            // 响应设置请求已结束
            return response.setComplete();  // 请求结束

        };
    }
}
