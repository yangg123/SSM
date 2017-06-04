package com.render.job.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

public class SimpleJob implements Job {
	public void execute(JobExecutionContext jobCtx)
			throws JobExecutionException {
		System.out.println(jobCtx.getTrigger().getDescription()
				+ " triggered. time is:" + (new Date()));
	}
}
