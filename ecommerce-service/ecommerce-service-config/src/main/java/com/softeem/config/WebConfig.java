package com.softeem.config;

import com.softeem.interceptor.AuthenticationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author LJL
 * @Date 2023:08:23:14:57
 **/
@Configuration
public class WebConfig {

    @Bean
    WebMvcConfigurer webMvcConfigurer(){
    return new WebMvcConfigurer() {
        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(new AuthenticationInterceptor() );
        }
    };
    }
}
