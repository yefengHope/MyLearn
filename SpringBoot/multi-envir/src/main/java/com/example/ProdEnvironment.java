package com.example;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Created by HF on 2018/1/6.
 */
@Service
@Profile("prod")
public class ProdEnvironment implements IMulti{


    @Override
    public void print() {
        System.out.println("生产环境");
    }

    @Override
    public void methodPrint() {
        System.out.println("生产环境：方法注解");
    }
}
