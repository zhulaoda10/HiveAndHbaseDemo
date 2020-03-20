package com.neo.quartz;



import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 自定义Job，实现Job接口并实现execute方法
 */
public  class HelloJob implements Job{

    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("execute job at " + new Date() + " by trigger " + context.getTrigger().getJobKey());
    }
    
}