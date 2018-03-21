package com.jk51.config;

import com.jk51.jobFactory.AutowiringSpringBeanJobFactory;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.spi.JobFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleThreadPoolTaskExecutor;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.sql.DataSource;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.Executor;

/**
 * 版权所有(C) 2017 上海银路投资管理有限公司
 * 描述:  quartz 设置
 * 作者: gaojie
 * 创建日期: 2018-03-16
 * 修改记录:
 */

@Configuration
@ConditionalOnProperty(name="quartz.enabled",havingValue = "true")
public class QuartzConfig {

    //@Autowired
    //private QuartzProperties prop;

    @Value("${quartz.enabled}")
    private Boolean enabled;
    @Value("${quartz.startupDelay}")
    private Long startupDelay;
    @Value("${quartz.prefix}")
    private String prefix;
    @Value("${quartz.threadCount}")
    private Integer threadCount;




    @Autowired
    private Scheduler scheduler;

    /**
     * 创建JobFactoryBean，在生成Job时将其添加到容器中，解决Job对象中不能自动注入容器管理的bean的问题
     * */
    @Bean
    public JobFactory jobFactory(){

        return new AutowiringSpringBeanJobFactory();

    }

    /**
     * 创建ScheduleFatory，生成Schedule bean,并启动Schedule
     *
     * */
    @Bean
    public Scheduler schedulerFactory(DataSource source, JobFactory jobFactory)throws Exception{

        SchedulerFactoryBean factory = new SchedulerFactoryBean();

        factory.setOverwriteExistingJobs(true);
        factory.setDataSource(source);
        factory.setJobFactory(jobFactory);
        factory.setQuartzProperties(quartzProperties());
        factory.afterPropertiesSet();

        Scheduler scheduler = factory.getScheduler();
        scheduler.setJobFactory(jobFactory);
        scheduler.start();
        return scheduler;
    }





    /**
     * 将class目录下的quartz.properties文件读到Properties对象中
     * */
    @Bean
    public Properties quartzProperties()throws Exception{

        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
        propertiesFactoryBean.afterPropertiesSet();
        return propertiesFactoryBean.getObject();
    }


    /**
     * 在应用停止前关闭schedule
     *
     * */
    @PreDestroy
    public void shutdownSchedule(){
        try {


            scheduler.shutdown();
        } catch (SchedulerException e) {

            //log something
            e.printStackTrace();
        }

    }

    /**
     * 应用启动时重启之前没运行完的任务
     *
     */
    /*@PostConstruct
    public void restoreJob() throws SchedulerException {

         scheduler.
    }
*/





}
