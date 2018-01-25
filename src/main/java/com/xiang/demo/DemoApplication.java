package com.xiang.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(DemoApplication.class);
		// banner开关
		// springApplication.setBannerMode(Banner.Mode.OFF);
		springApplication.run(args);
	}
}
