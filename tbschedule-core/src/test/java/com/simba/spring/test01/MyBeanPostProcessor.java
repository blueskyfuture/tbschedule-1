package com.simba.spring.test01;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * 后置处理器，初始化前后进行处理
 */
@Component
public class MyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        System.out.println("MyBeanPostProcessor...postProcessBeforeInitialization..."+s+"==>"+o);
        return o;
    }

    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        System.out.println("MyBeanPostProcessor...postProcessAfterInitialization..."+s+"==>"+o);
        return o;
    }
}
