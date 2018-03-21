package com.jk51.jobFactory;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

/**
 * 版权所有(C) 2017 上海银路投资管理有限公司
 * 描述:
 * 作者: gaojie
 * 创建日期: 2018-03-16
 * 修改记录:
 */
public class AutowiringSpringBeanJobFactory extends SpringBeanJobFactory {



    @Autowired
    private ApplicationContext context;


    /**
     * 在创建Job实例时将其注入到容器中，解决在Job对象中无法注入容器管理的bean的问题
     *
     * */
    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle)throws Exception{

        Object job = super.createJobInstance(bundle);
        context.getAutowireCapableBeanFactory().autowireBean(job);
        return job;

    }
}
