package com.softeem.config;

// 认证服务器配置类

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.annotation.Resource;

@Configuration
@EnableAuthorizationServer  // 当前类是一个认证服务器配置类
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Resource
    private ClientOAuth2DataConfigurationProperties clientOAuth2DataConfiguration;
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private RedisTokenStore tokenStore;
    @Resource
    private UserDetailsService userDetailsService;

    /**
     * 令牌接口的暴露相关配置，和令牌约束相关配置
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        // 是否允许访问Access_token的公钥
        // RSA:双向加密， 公钥:加密   私钥:解密
        security.tokenKeyAccess("permitAll()");
        // 是否允许访问校验凭证的接口 默认是受保护的
        // oauth/check_token
        security.checkTokenAccess("permitAll()");
    }


    /// C：用户正在使用的应用（第三方，也可以是己方应用）   S：认证服务器

    /**
     * <h2>客户端授权模型配置</h2>
     * <h3>客户端需要提供哪些许可证才可以访问认证服务器</h3>
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient(clientOAuth2DataConfiguration.getClientId())
                .scopes(clientOAuth2DataConfiguration.getScopes())
                .secret(passwordEncoder.encode(clientOAuth2DataConfiguration.getSecret()))
                .refreshTokenValiditySeconds(clientOAuth2DataConfiguration.getRefreshTokenValidityTime())
                .accessTokenValiditySeconds(clientOAuth2DataConfiguration.getTokenValidityTime())
                .authorizedGrantTypes(clientOAuth2DataConfiguration.getGrantTypes());
    }

    /**
     * endpoints： 已经走完了security的过滤器链，需要进行自定义认证流程的端点
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.userDetailsService(userDetailsService)
                .tokenStore(tokenStore)
                .authenticationManager(authenticationManager);
    }
}