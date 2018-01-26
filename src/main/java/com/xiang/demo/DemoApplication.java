package com.xiang.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Spring Boot 入口类
 */
@SpringBootApplication
@EnableScheduling  // 通过@EnableScheduling注解开启对计划任务的支持
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(DemoApplication.class);
		// banner开关
		// springApplication.setBannerMode(Banner.Mode.OFF);
		springApplication.run(args);
	}
}
