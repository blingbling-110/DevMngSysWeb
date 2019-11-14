package com.qzj.devmngsys.config;

import com.qzj.devmngsys.bean.LoginHandlerInterceptor;
import com.qzj.devmngsys.bean.paramLocaleResolver;
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
        registry.addViewController("/dashboard.html");
    }

    @Bean
    public LocaleResolver localeResolver() {
        return new paramLocaleResolver();//使用自定义区域信息解析器，解析URL中区域信息参数
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/", "/login", "/**/*.css");
    }
}
