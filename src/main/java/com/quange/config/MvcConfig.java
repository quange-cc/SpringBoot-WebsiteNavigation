package com.quange.config;


import com.quange.controller.interceptor.ProjectInterceptor;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Controller
public class MvcConfig implements WebMvcConfigurer {

    //实现WebMvcConfigurer接口，重写addInterceptors方法，实现拦截器注册

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // 拦截api 请求
        registry.addInterceptor(new ProjectInterceptor()).addPathPatterns("/api/**").excludePathPatterns(
                "/api/login", "/api/sites", "/api/getAside", "/api/getWebSitesById",
                "/api/getBackgroundStyle");

    }
}
