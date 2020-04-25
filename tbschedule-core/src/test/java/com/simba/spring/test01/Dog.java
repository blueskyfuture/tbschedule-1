package com.simba.spring.test01;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class Dog {
    public Dog(){
        System.out.println("Dog...contructor...");
    }

    //初始化对象之前
    @PostConstruct
    public void init(){
        System.out.println("Dog...init...");
    }

    //容器移除对象之前
    @PreDestroy
    public void destroy(){
        System.out.println("Dog...destroy...");
    }
}
