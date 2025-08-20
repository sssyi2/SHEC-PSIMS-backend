package com.webtab.shecpsims.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.defaultContentType(MediaType.APPLICATION_JSON);
    }
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:5173")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600); // 预检请求的有效期，单位为秒
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 开发环境路径（指向源码目录）
        registry.addResourceHandler("/upload/**")
                .addResourceLocations(
                        "file:src/main/resources/static/upload/",  // 开发时直接监控源码目录
                        "classpath:/static/upload/"               // 打包后访问编译目录
                );
        registry.addResourceHandler("/images/**")
                .addResourceLocations( "file:src/main/resources/static/images/",  // 开发时直接监控源码目录
                        "classpath:/static/images/"      );
    }
    }
