package com.xiang.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author xjc
 *
 * 定时任务类
 *
 * Spring官方Demo地址: http://spring.io/guides/gs/scheduling-tasks/
 */

@Component
public class ScheduledTasksController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledTasksController.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    /**
     * @Scheduled(fixedRate = 5000) ：上一次开始执行时间点之后5秒再执行
     * @Scheduled(fixedDelay = 5000) ：上一次执行完毕时间点之后5秒再执行
     * @Scheduled(initialDelay=1000, fixedRate=5000) ：第一次延迟1秒后执行，之后按fixedRate的规则每5秒执行一次
     * @Scheduled(cron="...") ：通过cron表达式定义规则
     */
    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        // TODO: 2018/1/26 定时任务：比如定时推送消息-邮件、短信 
        System.out.println("每隔5秒执行一次：" + dateFormat.format(new Date()));
        // log.info("The time is now {}", dateFormat.format(new Date()));
    }

    /**
     * 使用cron属性可按照指定时间执行，本例指的是每天20点07分执行；
     * cron是UNIX和类UNIX(Linux)系统下的定时任务
     */
    @Scheduled(cron = "0 07 20 ? * *" )
    public void fixTimeExecution(){
        System.out.println("在指定时间 "+dateFormat.format(new Date())+" 执行");
    }
}
