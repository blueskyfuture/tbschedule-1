package com.simba.spring;

import com.taobao.pamirs.schedule.strategy.TBScheduleManagerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Test {
    public static void main(String[] args) {
        //TimerTest();
        ScheduledExecutorTest();

    }

    private static void ScheduledExecutorTest() {
        ScheduledExecutorService service = new ScheduledThreadPoolExecutor(5);
        RunnableTest runnableTest = new RunnableTest("BBB");
        service.scheduleAtFixedRate(runnableTest,5000,2000, TimeUnit.MILLISECONDS);
    }

    private static void TimerTest() {
        Timer timer = new Timer("TBScheduleManagerFactory-Timer");
        TimerTaskTest timerTask = new TimerTaskTest("AAA");
        timer.schedule(timerTask, 5000, 2000);
    }
}

class RunnableTest implements Runnable {
    private static transient Logger log = LoggerFactory.getLogger(RunnableTest.class);
    String name;
    int count = 0;

    public RunnableTest(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        log.info("RunnableTest-run-begin..."+name+",count:"+count);
        count++;
    }
}


class TimerTaskTest extends java.util.TimerTask {

    private static transient Logger log = LoggerFactory.getLogger(TimerTaskTest.class);
    String name;
    int count = 0;

    public TimerTaskTest(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        log.info("TimerTaskTest-run-begin..."+name+",count:"+count);
        count++;
    }
}
