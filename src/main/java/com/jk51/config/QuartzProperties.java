package com.jk51.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


/**
 * 版权所有(C) 2017 上海银路投资管理有限公司
 * 描述:
 * 作者: gaojie
 * 创建日期: 2018-03-19
 * 修改记录:
 */
@Component
@Configuration
@ConfigurationProperties(prefix="quartz")
public class QuartzProperties {

    private Boolean enable;
    private Long startupDelay;
    private String prefix;
    private Integer threadCount;

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Long getStartupDelay() {
        return startupDelay;
    }

    public void setStartupDelay(Long startupDelay) {
        this.startupDelay = startupDelay;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public Integer getThreadCount() {
        return threadCount;
    }

    public void setThreadCount(Integer threadCount) {
        this.threadCount = threadCount;
    }
}
