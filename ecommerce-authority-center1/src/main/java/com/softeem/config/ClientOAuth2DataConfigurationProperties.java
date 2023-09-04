package com.softeem.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "client.oauth2")
@Data
@Component
public class ClientOAuth2DataConfigurationProperties {
    // grantTypes: password
    private String clientId;
    private String secret;
    private String [] grantTypes;
    private int tokenValidityTime;
    private int refreshTokenValidityTime;
    private String[] scopes;
}
