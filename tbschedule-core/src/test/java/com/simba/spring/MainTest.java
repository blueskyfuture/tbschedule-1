package com.simba.spring;

import com.simba.spring.test02.MyCat;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainTest {
    public static void main(String[] args) {
        MainTest test = new MainTest();
        test.method();
    }

    private void method(){
        ApplicationContext ctx = new AnnotationConfigApplicationContext(MainConfigOfLifeCycle.class);
        MyCat cat = (MyCat) ctx.getBean("myCat");
        System.out.println("annotation MyCat:"+cat);
    }

}
