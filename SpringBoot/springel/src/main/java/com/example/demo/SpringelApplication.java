package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@SpringBootApplication
public class SpringelApplication {

	// @Bean
	// private static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
	// 	return new PropertySourcesPlaceholderConfigurer();
	// }

	@Value("${book.info}")
	private String info;

	private static String staticInfo;

	@PostConstruct
	private void setStaticInfo() {
		staticInfo = info;
	}

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(SpringelApplication.class, args);
		// ElConfig elConfig = applicationContext.getBean(ElConfig.class);
		// System.out.println(elConfig.toString());
		System.out.println(staticInfo);
	}
}
