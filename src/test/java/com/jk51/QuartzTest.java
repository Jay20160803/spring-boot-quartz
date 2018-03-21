package com.jk51;

import org.junit.Test;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * 版权所有(C) 2017 上海银路投资管理有限公司
 * 描述:
 * 作者: gaojie
 * 创建日期: 2018-03-16
 * 修改记录:
 */
public class QuartzTest {


    @Test
    public void test1()throws Exception{

        SchedulerFactory schedulerFactory = new StdSchedulerFactory();

        Scheduler scheduler = schedulerFactory.getScheduler();

        scheduler.start();


        JobDetail job = newJob(HelloJob.class)
                .withIdentity("myJob","group1")
                .build();


        Trigger trigger = newTrigger()
                .withIdentity("myTrigger","group1")
                .startNow()
                .withSchedule(simpleSchedule()
                    .withIntervalInSeconds(40)
                    .repeatForever())
                .build();

        scheduler.scheduleJob(job,trigger);

        scheduler.getContext();
        scheduler.getCurrentlyExecutingJobs();
        scheduler.getJobDetail(new JobKey("myJob","group1"));
        scheduler.getTrigger(new TriggerKey("myTrigger","group1"));
        scheduler.getSchedulerInstanceId();
        scheduler.getCurrentlyExecutingJobs();
        scheduler.getContext();
        scheduler.getCalendarNames();
        scheduler.getListenerManager();
        scheduler.getJobGroupNames();
        scheduler.getMetaData();
        scheduler.getPausedTriggerGroups();



        Thread.sleep(50000);

        scheduler.shutdown();

    }
}
