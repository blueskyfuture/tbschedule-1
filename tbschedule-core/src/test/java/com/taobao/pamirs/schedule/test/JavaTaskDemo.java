package com.taobao.pamirs.schedule.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.taobao.pamirs.schedule.strategy.IStrategyTask;

import java.util.concurrent.TimeUnit;

public class JavaTaskDemo implements IStrategyTask, Runnable {
    protected static transient Logger log = LoggerFactory.getLogger(JavaTaskDemo.class);

    private String parameter;
    private boolean stop = false;

    public void initialTaskParameter(String strategyName, String taskParameter) {
        parameter = taskParameter;
        new Thread(this).start();
    }

    @Override
    public void stop(String strategyName) throws Exception {
        this.stop = true;
    }

    @Override
    public void run() {
        while (stop == false) {
            log.error("执行任务：" + this.parameter);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        JavaTaskDemo javaTaskDemo = new JavaTaskDemo();
        javaTaskDemo.initialTaskParameter("strategyName","taskParameter");
        //线程睡眠指定时间
        try {
            TimeUnit.MILLISECONDS.sleep(3000);
            javaTaskDemo.stop("strategyName");
            log.info("stop task finish...");
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

}
