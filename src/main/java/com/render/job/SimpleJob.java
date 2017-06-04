package com.render.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * Created by yg on 2017/3/6.
 */
public class SimpleJob extends QuartzJobBean {

    private int timeout;
    private static int i = 0;

    //调度工厂实例化后，经过timeout时间开始执行调度
    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    /**
     * 要调度的具体任务
     */
    @Override
    protected void executeInternal(JobExecutionContext context)
            throws JobExecutionException {

        System.out.println("继承QuartzJobBean的方式-调度" + ++i + "进行中...");
    }
}