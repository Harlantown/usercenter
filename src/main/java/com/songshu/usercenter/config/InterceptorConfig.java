package com.songshu.usercenter.config;

import com.songshu.usercenter.controller.InterceptorHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.annotation.WebListener;

/**
 * @author Charmot
 * @Description
 * @create 2022-05-10 下午 9:38
 */
@Configuration
@Slf4j
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new InterceptorHandler())
                .addPathPatterns("/**")
                .excludePathPatterns("/api/user/login","/api/user/register",
                                     "/user/login", "/user/register");
    }
}
