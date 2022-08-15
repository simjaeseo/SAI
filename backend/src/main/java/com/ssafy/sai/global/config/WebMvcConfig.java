package com.ssafy.sai.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private String connectPath = "/members/**";
    private String resourcePath = "file:vue/dist/img";

    /**
     * ResourceHandlerRegistry : 리소스 등록 및 핸들러 관리
     * 리소스 위치와 매칭할 URL 등록
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("connectPath").addResourceLocations("resourcePath");
    }
}
