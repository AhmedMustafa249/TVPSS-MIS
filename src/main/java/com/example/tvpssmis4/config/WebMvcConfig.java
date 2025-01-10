package com.example.tvpssmis4.config;

import com.example.tvpssmis4.interceptor.RoleBasedInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private RoleBasedInterceptor roleBasedInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(roleBasedInterceptor)
                .addPathPatterns("/gpm/**", "/ppd/**", "/jpnj/**");
    }
}
