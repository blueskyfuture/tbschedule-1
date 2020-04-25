package com.simba.spring;

import com.simba.spring.test01.Monkey;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//@ComponentScan("com.simba.spring.test01")
@ComponentScan("com.simba.spring.test02")
@Configuration
public class MainConfigOfLifeCycle {
    @Bean(initMethod = "init",destroyMethod = "destroy")
    public Monkey monkey(){
        return new Monkey();
    }
}
