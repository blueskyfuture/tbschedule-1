package com.simba.spring.test02;

import com.taobao.pamirs.schedule.strategy.TBScheduleManagerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Component
public class MyCat implements InitializingBean, DisposableBean, ApplicationContextAware {
    private ApplicationContext applicationcontext;

    public MyCat(){
        System.out.println("MyCat...construct...");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("MyCat...afterPropertiesSet...begin...");
        startTbs();
        System.out.println("MyCat...afterPropertiesSet...end...");
    }

    /**
     * 此处启动tbs
     * @throws IOException
     */
    private void startTbs() throws IOException {
        InputStream in = MyCat.class.getClassLoader().getResourceAsStream("schedule-conf.properties");
        Properties p = new Properties();
        p.load(in);
        TBScheduleManagerFactory scheduleManagerFactory = new TBScheduleManagerFactory();
        scheduleManagerFactory.setApplicationContext(this.applicationcontext);
        try {
            scheduleManagerFactory.init(p);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("MyCat...destroy...");
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationcontext = applicationContext;
    }
}
