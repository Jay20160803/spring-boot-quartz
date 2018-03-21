package com.jk51.controller;

import com.jk51.service.ScheduleManager;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 版权所有(C) 2017 上海银路投资管理有限公司
 * 描述:
 * 作者: gaojie
 * 创建日期: 2018-03-20
 * 修改记录:
 */
@RestController
@RequestMapping("quartz/simpleJob")
public class QuartzController {

    @Autowired
    private ScheduleManager scheduleManager;

    /**
     * 添加Job到Schedule
     * */
    @RequestMapping(value = "jobs",method = RequestMethod.POST)
    public void addJob(@RequestParam String name, @RequestParam String group){

        scheduleManager.registerJob(name,group);

    }

    /**
     * 添加Trigger，并触发指定的Job
     *
     * */
    @RequestMapping(value="triggers",method = RequestMethod.POST)
    public void addTrigger(@RequestParam String jobName, @RequestParam String jobGroup,@RequestParam String triggerName,@RequestParam String triggerGroup){

        try {
            scheduleManager.registerTrigger(jobName,jobGroup,triggerName,triggerGroup);
        } catch (SchedulerException e) {

            //log
            e.printStackTrace();
        }

    }


    /**
     * 为SimpleJob删除trigger
     * */
    @RequestMapping(value = "trggiers",method = RequestMethod.DELETE)
    public void deleteTrggiers(@RequestParam String name, @RequestParam String group){

        scheduleManager.deleteTrggiers(name,group);
    }

}
