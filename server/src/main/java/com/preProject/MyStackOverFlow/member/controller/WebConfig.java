package com.preProject.MyStackOverFlow.member.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/*") // 모든 경로에 대해 CORS 허용
                .allowedOrigins("") // 모든 도메인에서의 요청 허용
                .allowedMethods("") // 모든 HTTP 메소드를 허용
                .allowedHeaders(""); // 모든 헤더를 허용
    }
}