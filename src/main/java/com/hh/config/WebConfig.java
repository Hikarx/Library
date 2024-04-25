package com.hh.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @Author hh
 * @Date 2024/4/25 13:41
 * @description:
 */

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Resource
    private LoginInterceptor loginInterceptor;

    @Resource
    private UserInterceptors userInterceptors;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/index.html",
                        "/borrow.html",
                        "/css/**",
                        "/js/**",
                        "/index/login",
                        "/favicon.ico"
                ).order(1);
        registry.addInterceptor(userInterceptors)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/index.html",
                        "/borrow.html",
                        "/css/**",
                        "/js/**",
                        "/index/login",
                        "/favicon.ico"
                ).order(0);
    }
}
