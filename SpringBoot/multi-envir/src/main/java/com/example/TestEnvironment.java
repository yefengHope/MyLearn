package com.example;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Created by HF on 2018/1/6.
 */
@Service
@Profile("test")
public class TestEnvironment implements IMulti{


    @Override
    public void print() {
        System.out.println("测试环境");
    }

    @Override
    @Profile("test")
    public void methodPrint() {
        System.out.println("测试环境：方法注解");
    }
}
