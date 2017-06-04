package com.render.job;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Spring Task 配置文件实现job
 * Created by yg on 17/2/18.
 */

@Component("TestJob")
public class TestJob {
    @Scheduled(cron = "0/5 * * * * ?")//每隔5秒隔行一次
    public void test1()
    {
        //System.out.println("job1 开始执行");
    }
    @Scheduled(cron = "0/5 * * * * ?")//每隔5秒隔行一次
    public void test2()
    {
        //System.out.println("job2 开始执行");
    }
}
