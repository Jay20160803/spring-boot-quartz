package com.jk51.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 版权所有(C) 2017 上海银路投资管理有限公司
 * 描述:
 * 作者: gaojie
 * 创建日期: 2018-03-16
 * 修改记录:
 */
public class SampleJob implements Job {


    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        System.out.print("execute job");
    }
}
