package com.jk51.service;

import com.jk51.dto.JobMeta;
import com.jk51.job.SampleJob;
import com.jk51.util.QuartzUtil;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import static com.jk51.util.QuartzUtil.*;

/**
 * 版权所有(C) 2017 上海银路投资管理有限公司
 * 描述:
 * 作者: gaojie
 * 创建日期: 2018-03-19
 * 修改记录:
 */
@Service
public class ScheduleManager {

    @Autowired
    private Scheduler scheduler;

    /**
     *  Spring初始化后，执行注册数据库中的Schedule任务
     *
     * */
    @PostConstruct
    private void registerAll()throws SchedulerException{


    }

    /**
     * controller 添加Job 和Trigger
     *
     * */
    public void register(){



    }


    /**
     * 删除Trigger
     *
     * 先暂停Trigger，在删除Trigger
     * */
    public void deleteTrggiers(String name, String group) {


        try {

            TriggerKey triggerKey =  createTriggerKey(name,group);
            scheduler.pauseTrigger(triggerKey);
            scheduler.unscheduleJob(triggerKey);

        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加Job
     *
     * */
    public void registerJob(String name, String group) {


        try {

            scheduler.addJob(createJobDetail(SampleJob.class,name,group),true);

        } catch (SchedulerException e) {

            //log
            e.printStackTrace();
        }
    }

    /**
     * 添加Trigger某个Job
     * */
    public void registerTrigger(String jobName, String jobGroup, String triggerName, String triggerGroup) throws SchedulerException {


        JobDetail job = scheduler.getJobDetail(createJobKey(jobName,jobGroup));
        Trigger trigger = createSimpleTriggerAtNow(triggerName,triggerGroup,job);

        scheduler.scheduleJob(trigger);
    }
}
