package com.taobao.pamirs.schedule.test;

import com.taobao.pamirs.schedule.CronExpression;

import java.util.Date;

public class CronExpressionTest {
    public static void main(String[] args) {
        String str = "0/20 * * * * ?";
        try{
            CronExpression cexp = new CronExpression(str);
            Date current = new Date(System.currentTimeMillis());
            Date nextTime = cexp.getNextValidTimeAfter(current);
            System.out.println("nextTime:"+nextTime);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }

    }
}
