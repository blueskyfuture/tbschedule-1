package com.autohome.test;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.taobao.pamirs.schedule.CronExpression;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.concurrent.*;

public class PauseOrResumeScheduleTaskTest  {

    public static void main(String[] args)throws Exception {
//        test01();
        test02();
    }

    public static void test02(){
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(3,
                new ThreadFactoryBuilder()
                        .setNameFormat("DiscoveryClient-%d")
                        .setDaemon(true)
                        .build());
        ThreadPoolExecutor executorService = new ThreadPoolExecutor(
                1, 10, 0, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>(),
                new ThreadFactoryBuilder()
                        .setNameFormat("DiscoveryClient-HeartbeatExecutor-%d")
                        .setDaemon(true)
                        .build()
        );
        scheduler.schedule(new HeartBeatTimerTask1("1001"),1L,TimeUnit.SECONDS);
    }

    public static void test01() throws ParseException {
        String heartBeatTask = "timer-task";
        String tmpStr = "*/20 * * * * ?";
        Timer heartBeatTimer = new Timer(heartBeatTask);
        CronExpression cexpStart = new CronExpression(tmpStr);
        Date current = new Date();
        Date firstStartTime = cexpStart.getNextValidTimeAfter(current);
        heartBeatTimer.schedule(
                new ScheduleTask(heartBeatTimer, ScheduleTask.TYPE_RESUME, tmpStr),
                firstStartTime,10 * 1000);
        heartBeatTimer.schedule(new HeartBeatTimerTask1("1001"), new Date(System.currentTimeMillis() + 5000),
                5*1000);
    }
}



class ScheduleTask extends java.util.TimerTask {

    private static transient Logger log = LoggerFactory.getLogger(PauseOrResumeScheduleTaskTest.class);
    public static int TYPE_PAUSE = 1;
    public static int TYPE_RESUME = 2;
    Timer timer;
    int type;
    String cronTabExpress;

    public ScheduleTask(Timer aTimer, int aType, String aCronTabExpress) {
        this.timer = aTimer;
        this.type = aType;
        this.cronTabExpress = aCronTabExpress;
    }

    @Override
    public void run() {
        try {
            System.out.println("ScheduleTask=="+Thread.currentThread().getName()+"-"+new Date());
            Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
            //this.cancel();// 取消调度任务
            Date current = new Date(System.currentTimeMillis());
            CronExpression cexp = new CronExpression(this.cronTabExpress);
            Date nextTime = cexp.getNextValidTimeAfter(current);
            //System.out.println("ScheduleTask=="+Thread.currentThread().getName()+"-nextTime:"+nextTime);
            //this.timer.schedule(new ScheduleTask(this.timer, this.type, this.cronTabExpress),nextTime);
        } catch (Throwable ex) {
            log.error(ex.getMessage(), ex);
        }
    }
}


class HeartBeatTimerTask1 extends java.util.TimerTask {

    private static transient Logger log = LoggerFactory.getLogger(PauseOrResumeScheduleTaskTest.class);
    private String name;
    public HeartBeatTimerTask1(String aManager) {
        name = aManager;
    }

    @Override
    public void run() {
        try {
            Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
            System.out.println("HeartBeatTimerTask1=="+Thread.currentThread().getName()+"-"+new Date());
            int random = new Random().nextInt(10);
            System.out.println("HeartBeatTimerTask1=="+Thread.currentThread().getName()+"-"+new Date()+"-random:"+random);
            if(random == 3){
                throw new RuntimeException("测试1001");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            log.error(ex.getMessage(), ex);
        }
    }
}