package com.joselara.crmusers.configuration;

import com.joselara.crmusers.interceptors.FeignClientInterceptor;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestConfiguration {

    @Bean
    public RequestInterceptor getFeignClientInterceptor() {
        return new FeignClientInterceptor();
    }
}
