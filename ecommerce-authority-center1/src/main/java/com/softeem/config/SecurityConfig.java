package com.softeem.config;

import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.crypto.digest.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 为什么把凭证存到redis中？
     * redis可以帮助我们实现凭证的续签
     * redis.set(key,token,5分钟)
     */

    @Resource
    private RedisConnectionFactory redisConnectionFactory;
    // 基于redis的凭证存储器
    @Bean
    public RedisTokenStore redisTokenStore(){
        RedisTokenStore redisTokenStore = new RedisTokenStore(redisConnectionFactory);
        redisTokenStore.setPrefix("TOKEN:");
        return redisTokenStore;
    }



    // 过滤器配置
    /**
     *  哪些过滤器要启用，哪些要禁用，认证过滤器要拦截哪些请求，哪些不拦截
     *  要加入哪些额外的过滤器，要适配哪些额外的异常处理器
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                // Spring-boot-starter-actuator /info /health
                // Spring-boot-starter-admin   仪表盘
                .antMatchers("/oauth/**","/actuator/**").permitAll()
                .anyRequest().authenticated();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return DigestUtil.md5Hex(charSequence.toString());
            }
            //  一个明文一个是密文
            //
            @Override
            public boolean matches(CharSequence charSequence, String s) {
                return DigestUtil.md5Hex(charSequence.toString()).equalsIgnoreCase(s);
            }
        };
    }

    public static void main(String[] args) {
        System.out.println(DigestUtil.md5Hex("123"));
    }
    /**
     * 负责整体的认证流程调度
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
