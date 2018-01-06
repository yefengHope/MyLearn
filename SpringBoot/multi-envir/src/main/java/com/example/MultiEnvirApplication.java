package com.example;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@SpringBootApplication
public class MultiEnvirApplication {

	@Resource
	private Environment environment;
    //
	// @Resource
	// private IMulti iMulti;
    //
	// private static Environment env;
    //
	// private static IMulti iMultiStatic;
    //
	// @PostConstruct
	// private void SetEnv() {
	// 	env = environment;
	// 	iMultiStatic = iMulti;
	// }

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(MultiEnvirApplication.class, args);
		Environment env = context.getEnvironment();
		String[] activeProfiles = env.getActiveProfiles();
		System.out.println(JSON.toJSONString(activeProfiles));
		context.getBeanFactory().getBean(IMulti.class).print();

		context.getBeanFactory().getBean(IMulti.class).methodPrint();

		context.getBeanFactory().getBean(IMultiMethod.class).printDev();
		context.getBeanFactory().getBean(IMultiMethod.class).printTest();
		context.getBeanFactory().getBean(IMultiMethod.class).printProd();
	}
}
