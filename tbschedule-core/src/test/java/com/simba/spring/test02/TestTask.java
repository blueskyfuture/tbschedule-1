package com.simba.spring.test02;

import com.taobao.pamirs.schedule.IScheduleTaskDealSingle;
import com.taobao.pamirs.schedule.TaskItemDefine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * tbs任务测试
 */
@Component
public class TestTask implements IScheduleTaskDealSingle<String> {
    private Logger logger = LoggerFactory.getLogger(TestTask.class);

    @Override
    public List<String> selectTasks(String taskParameter, String ownSign, int taskItemNum,
                                             List<TaskItemDefine> taskItemList, int eachFetchDataNum) throws Exception {
        logger.info("TestTask selectTasks");
        List<String> resourceIdList = getWrapperSms();
        logger.info("TestTask需要发送短信数量:"+resourceIdList.size());
        return resourceIdList;
    }

    @Override
    public boolean execute(String model, String ownSign) throws Exception {
        logger.info("TestTask begin..."+model);
        //线程睡眠指定时间
        try {
            TimeUnit.MILLISECONDS.sleep(2000);
        }catch (InterruptedException ex){
            ex.printStackTrace();
        }
        logger.info("TestTask end..."+model);
        return true;
    }

    @Override
    public Comparator<String> getComparator() {
        return new Comparator<String>() {
            public int compare(String o1, String o2) {
                int i = o1.compareTo(o2);
                return i;
            }

            public boolean equals(Object obj) {
                boolean result = (this == obj);
                logger.info("getComparator equals:{}",result);
                return result;
            }
        };
    }

    private List<String> getWrapperSms(){
        logger.info("TestTask getWrapperSms..begin...");
        List<String> all = new ArrayList<>();
        int nextInt = new Random().nextInt(10);
        logger.info("TestTask getWrapperSms..nextInt:{}",nextInt);
        if(nextInt == 8){
            logger.info("TestTask getWrapperSms实际查询");
            List<String> reList = getSms();
            all.addAll(reList);
        }else if(nextInt==9){
            logger.info("TestTask getWrapperSms实际查询重复验证");
            List<String> reList = getSms();
            all.addAll(reList);
            all.addAll(reList);
        }else{
            logger.info("TestTask getWrapperSms返回空");
        }
        logger.info("TestTask getWrapperSms..end...collect.size:{},collect:{}",all.size(),all);
        return all;
    }

    private List<String> getSms(){
        List<String> reList = Arrays.asList("a","b","c");
        return reList;
    }

}
