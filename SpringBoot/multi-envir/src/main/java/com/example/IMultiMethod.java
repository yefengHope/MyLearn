package com.example;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Created by HF on 2018/1/6.
 */
@Service
public class IMultiMethod {

    @Profile("test")
    public void printTest() {
        System.out.println("test");
    }

    @Profile("dev")
    public void printDev() {
        System.out.println("dev");
    }

    @Profile("prod")
    public void printProd() {
        System.out.println("prod");
    }
}
