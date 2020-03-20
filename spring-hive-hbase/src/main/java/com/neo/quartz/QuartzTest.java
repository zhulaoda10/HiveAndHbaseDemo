package com.neo.quartz;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;
public class QuartzTest {
	public static void main(String[] args) {
		 try {
	            //1.从StdSchedulerFactory工厂中获取一个任务调度器
	            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

	            //2. 启动调度器
	            scheduler.start();
	            System.out.println("scheduler is start...");
                //3.添加定时任务
	            //3.1 定义job
	            JobDetail job = newJob(HelloJob.class)
	                    .withIdentity("job1", "group1")
	                    .build();
	            //  3.2 定义Trigger，使得job现在就运行，并每隔3s中运行一次，重复运行5次, withRepeatCount(number)设定job运行次数为number+1
	            Trigger trigger = newTrigger()
	                    .withIdentity("trigger1", "group1")
	                    .startNow()
	                    .withSchedule(simpleSchedule()
	                        .withIntervalInSeconds(3)
	                        .withRepeatCount(4))
	                    .build();

	            //  3.3 交给scheduler去调度
	            scheduler.scheduleJob(job, trigger);
	            //关闭调度器
	            //scheduler.shutdown();
	        } catch (SchedulerException e) {
	            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
	        }
		 
		 
		
	}
}
