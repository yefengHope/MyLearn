package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by HF on 2018/1/13.
 */
// @Component
public class Book {

    @Value("${book.author}")
    private String author;

    @Value("${book.name}")
    private String name;

    @Value("我是固定字符串")
    private String fixId;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFixId() {
        return fixId;
    }

    public void setFixId(String fixId) {
        this.fixId = fixId;
    }
}
