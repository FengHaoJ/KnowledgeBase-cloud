package com.kb.common.config;

import com.kb.common.interceptor.UserInfoInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ConditionalOnClass(DispatcherServlet.class)
public class MvcConfig implements WebMvcConfigurer {
    public MvcConfig(){
        System.out.println("---如果有输出，则说明被实例化，被扫描到");
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        System.out.println("拦截器");
        registry.addInterceptor(new UserInfoInterceptor());
    }
}
