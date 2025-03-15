package com.sky.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;


/**
 * 定时任务类
 */
@Component
@Slf4j
public class MyTask {

    /**
     * 定时任务，五秒执行一次
     */
//    @Scheduled(cron = "0-5 * * * * ?")// 秒，分，时，日，月，周，年（可选）
    public void executeTask(){
        log.info("执行定时任务:{}",new Date());
    }


}
