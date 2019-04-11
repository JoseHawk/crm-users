package com.joselara.crmusers.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

@EnableAuthorizationServer
@Configuration
public class AuthorizationConfiguration extends AuthorizationServerConfigurerAdapter {

    @Value("${security.oauth2.client.client-id}")
    private String CLIENT_ID;

    @Value("${security.oauth2.client.client-secret}")
    private String CLIENT_SECRET;

    @Value("${security.oauth2.client.authorized-grant-types}")
    private String[] GRANT_TYPES;

    @Value("${security.oauth2.client.authorities}")
    private String[] AUTHORITIES;

    @Value("${security.oauth2.client.scope}")
    private String SCOPE[];

    @Value("${security.oauth2.client.access-token-validity-seconds}")
    private int TOKEN_VALIDATY;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenStore tokenStore;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients
                .inMemory()
                .withClient(CLIENT_ID)
                .secret(CLIENT_SECRET)
                .authorizedGrantTypes(GRANT_TYPES)
                .authorities(AUTHORITIES)
                .scopes(SCOPE)
                .accessTokenValiditySeconds(TOKEN_VALIDATY)
                .refreshTokenValiditySeconds(TOKEN_VALIDATY);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
                .tokenStore(tokenStore)
                .authenticationManager(authManager)
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);;
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                .allowFormAuthenticationForClients();
    }
}
