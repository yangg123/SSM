/*
 * Created on Sep 21, 2006
 * 
 * This class is to run a scheduler with SimpleTrigger
 */
package com.render.job.quartz;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

public class SimpleTriggerRunner {
//	public static void main(String args[]) {
//		try {
//
//			JobDetail jobDetail = new JobDetail("job1_1", "jgroup1",
//					SimpleJob.class);
//
//			SimpleTrigger simpleTrigger = new SimpleTrigger("trigger1_1",
//					"tgroup1");
//			simpleTrigger.setStartTime(new Date());
//			simpleTrigger.setRepeatInterval(2000);
//			simpleTrigger.setRepeatCount(100);
//
//			SchedulerFactory schedulerFactory = new StdSchedulerFactory();
//			Scheduler scheduler = schedulerFactory.getScheduler();
//			scheduler.scheduleJob(jobDetail, simpleTrigger);
//			scheduler.start();
//		} catch (exception e) {
//			e.printStackTrace();
//		}
//
//	}

}
