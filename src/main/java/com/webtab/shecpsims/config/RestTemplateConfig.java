package com.webtab.shecpsims.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;

import java.time.Duration;

/**
 * RestTemplate配置类
 * 用于Java后端调用外部API，包括Flask端
 */
@Configuration
public class RestTemplateConfig {
    
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    
    /**
     * Flask端专用RestTemplate
     * 配置了适当的超时时间
     */
    @Bean("flaskRestTemplate")
    public RestTemplate flaskRestTemplate() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setConnectTimeout(30000); // 30秒连接超时
        factory.setReadTimeout(60000);    // 60秒读取超时
        
        RestTemplate restTemplate = new RestTemplate(factory);
        return restTemplate;
    }
}