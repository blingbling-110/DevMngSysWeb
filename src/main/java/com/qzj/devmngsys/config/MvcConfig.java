package com.qzj.devmngsys.config;

import com.qzj.devmngsys.bean.LoginHandlerInterceptor;
import com.qzj.devmngsys.bean.ParamLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/dashboard").setViewName("dashboard");
    }

    @Bean
    public LocaleResolver localeResolver() {
        return new ParamLocaleResolver();//使用自定义区域信息解析器，解析URL中区域信息参数
    }

    @Bean
    public LoginHandlerInterceptor loginHandlerInterceptor() {
        return new LoginHandlerInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginHandlerInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/index", "/login", "/asserts/**", "/favicon.ico");
    }
}
