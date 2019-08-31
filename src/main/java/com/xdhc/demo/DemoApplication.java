package com.xdhc.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = {"com.xdhc.demo"})
@MapperScan("com.xdhc.demo.dao")
@EnableSwagger2
//扫描 所有需要的包, 包含一些自用的工具类包 所在的路径
//@ComponentScan(basePackages= {"com.xdhc.demo"})
//开启定时任务
//@EnableScheduling
//开启异步调用方法
@EnableAsync
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
