package com.xdhc.demo.config.web;

import com.google.common.base.Predicates;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
/**
 * 如果application.properties 里没有配置 swagger.enable=true就关闭Swagger2，如果配置成swagger.enable=false也是关闭
 */
@ConditionalOnProperty(prefix = "swagger",value = {"enable"},havingValue = "true")
@EnableSwagger2
public class SwaggerConfiguration {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo())
            .pathMapping("/")
            .globalOperationParameters(setHeaderToken())
            .select() // 选择那些路径和api会生成document
            .apis(RequestHandlerSelectors.any())// 对所有api进行监控
            //不显示错误的接口地址
            .paths(Predicates.not(PathSelectors.regex("/error.*")))//错误路径不监控
            .paths(PathSelectors.regex("/.*"))// 对根下所有路径进行监控
            .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("xdhc304")
            .contact(new Contact("xdhc304", "", "1171286725@qq.com"))
            .description("这是SWAGGER_2生成的接口文档")
            .termsOfServiceUrl("NO terms of service")
            .license("The Apache License, Version 2.0")
            .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
            .version("v1.0")
            .build();
    }

    private List<Parameter> setHeaderToken() {
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        /**
         * 添加header X-Auth-Token 参数
         */
        tokenPar.name("X-Auth-Token").description("token").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(tokenPar.build());
        return pars;
    }

}
