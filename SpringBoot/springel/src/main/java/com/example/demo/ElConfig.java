package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

/**
 * Created by HF on 2018/1/13.
 */
// @Component
// @PropertySource("classpath:test.properties")
public class ElConfig {

    @Value("I Love you")
    private String normal;

    // idea 会提示这个属性，但是获取报错
    // @Value("#{systemProperties.os.name}")
    private String osName;

    @Value("#{book.author}")
    private String fromBookAuthor;

    @Value("#{systemProperties['os.name']}")
    private String osName1;

    @Value("#{T(java.lang.Math).random()*100.0}")
    private double randomNumber;

    @Value("classpath:test.txt")
    private Resource testTxt;

    @Value("http://www.baidu.com")
    private Resource testUrl;

    @Override
    public String toString() {
        return "ElConfig{" +
                "normal='" + normal + '\'' +
                ", osName='" + osName + '\'' +
                ", fromBookAuthor='" + fromBookAuthor + '\'' +
                ", osName1='" + osName1 + '\'' +
                ", randomNumber=" + randomNumber +
                ", testTxt=" + testTxt +
                ", testUrl=" + testUrl +
                '}';
    }
}
