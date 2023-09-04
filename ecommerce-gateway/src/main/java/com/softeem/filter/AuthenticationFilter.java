package com.softeem.filter;

import com.softeem.exception.ParameterException;
import com.softeem.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


/**
 * @Author LJL
 * @Date 2023:08:23:14:29
 **/
@Component
public class AuthenticationFilter implements GlobalFilter, Ordered {


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        /* Todo
         *   1、白名单放行
         *   2、获取请求uri
         *   3、解析凭证
         * */
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        String path = request.getURI().getPath();//   /oauth/login

        if (path.contains("/oauth/login")) {
            return chain.filter(exchange);
        }
        String token = request.getHeaders().getFirst("token");
        Claims parse = JwtUtil.parse(token);
        if (parse == null) {
            throw new ParameterException("凭证有误！");
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE+1;
    }
}
