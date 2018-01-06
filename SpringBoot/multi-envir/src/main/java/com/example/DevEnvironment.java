package com.example;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Created by HF on 2018/1/6.
 */
@Service
@Profile("dev")
public class DevEnvironment implements IMulti{


    @Override
    public void print() {
        System.out.println("开发环境");
    }

    @Override
    @Profile("dev")
    public void methodPrint() {
        System.out.println("开发环境：方法注解");
    }
}
