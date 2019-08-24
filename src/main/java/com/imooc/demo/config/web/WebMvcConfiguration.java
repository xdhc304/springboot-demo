package com.imooc.demo.config.web;

import com.imooc.demo.web.interceptor.Interceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /**
         * 拦截器按照顺序执行
         */
//        registry.addInterceptor(new Interceptor()).addPathPatterns("/area/**")
//                .addPathPatterns("/one/**");

        registry.addInterceptor(new Interceptor()).addPathPatterns("/area/**"); // /*/**

        super.addInterceptors(registry);
    }
}
