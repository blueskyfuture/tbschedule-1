package com.free.tbschedule.demo.task;

import com.free.tbschedule.demo.task.dto.User;
import com.free.tbschedule.demo.task.service.UserService;
import com.taobao.pamirs.schedule.IScheduleTaskDealSingle;
import com.taobao.pamirs.schedule.TaskItemDefine;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 简单任务示例
 */
@Slf4j
public class SampleTask implements IScheduleTaskDealSingle<User> {

    @Autowired
    private UserService userService;

    @Override
    public List<User> selectTasks(String taskParameter, String ownSign, int taskItemNum,
        List<TaskItemDefine> taskItemList, int eachFetchDataNum) {
        log.info("taskParameter:"+taskParameter+",taskItemList.size:"+taskItemList.size()+",eachFetchDataNum:"+eachFetchDataNum+",currentThread:"+Thread.currentThread().getName());
        List<Integer> taskItemIds = taskItemList.stream()
            .map(TaskItemDefine::getTaskItemId)
            .map(Integer::valueOf)
            .collect(Collectors.toList());
        log.info("taskItemIds.size:"+taskItemIds.size() + ",taskItemIds.toString:" + taskItemIds.toString());
        List<User> users = userService.getUsers(taskItemNum, taskItemIds, eachFetchDataNum);
        log.info("获取到{}条待处理的用户,currentThread:{}", users.size(),Thread.currentThread().getName());
        return users;
    }

    @Override
    public boolean execute(User task, String ownSign) {
        try {
            log.info("开始处理用户：{},currentThread:{}", task,Thread.currentThread().getName());
            task.setAge(task.getAge() + 1);
            userService.update(task);
            return true;
        } catch (Exception e) {
            log.error("处理失败：user:{},currentThread:{}", task,Thread.currentThread().getName());
            return false;
        }
    }

    @Override
    public Comparator<User> getComparator() {
        // 可不实现
        return null;
    }
}
