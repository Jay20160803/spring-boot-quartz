package com.jk51.util;

import com.jk51.dto.JobMeta;
import org.quartz.*;

import java.util.Date;

import static org.quartz.CronScheduleBuilder.cronSchedule;
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
public class QuartzUtil {


    public static JobDetail createJobDetail(Class jobClass, String name,String group){

        JobDetail jobDetail = newJob(jobClass)
                .withIdentity(name,group)
                .storeDurably()
                .build();

        return jobDetail;

    }


    /**
     * 创建SimpleTrigger
     * @Param name
     * @Param group
     * @Param startDate  触发器执行时间
     * */
    public static SimpleTrigger createSimpleTrigger(String name, String group, Date startDate, JobDetail job){

        SimpleTrigger trigger = newTrigger()
                .withIdentity(name,group)
                .startAt(startDate)                 //如果没有指定startAt，默认立即开始
                .withSchedule(simpleSchedule()      //如果没有指定ScheduleBuild，默认只执行1次
                    .withIntervalInSeconds(3)
                    .withRepeatCount(10))
                .forJob(job)
                .build();

        return trigger;
    }

    /**
     * 创建SimpleTrigger at Now
     * */
    public static SimpleTrigger createSimpleTriggerAtNow(String name, String group, JobDetail job){

        return createSimpleTrigger(name,group,new Date(),job);
    }


    /**
     * 创建CronTrigger
     *
     * */
    public static CronTrigger createCronTrigger(String name, String group,String cronExpression){

        CronTrigger trigger = newTrigger()
                .withIdentity(name,group)
                .withSchedule(cronSchedule(cronExpression))
                .build();

        return trigger;

    }

    /**
     * 创建JobKey
     * */
    public static JobKey createJobKey(String name,String group){

        return new JobKey(name,group);
    }

    /**
     *
     * 创建TriggerKey
     * */
    public static TriggerKey createTriggerKey(String name,String group){

        return new TriggerKey(name,group);

    }
}
