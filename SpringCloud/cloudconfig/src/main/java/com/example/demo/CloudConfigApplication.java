package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer // 开启配置中心
@SpringBootApplication
public class CloudConfigApplication {

	public static void main(String[] args) {
		// no.1
		SpringApplication.run(CloudConfigApplication.class, args);
		// no.2
		// new SpringApplicationBuilder(CloudConfigApplication.class).web(true).run(args);
	}
}
