package com.jk51.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * 版权所有(C) 2017 上海银路投资管理有限公司
 * 描述:
 * 作者: gaojie
 * 创建日期: 2018-03-19
 * 修改记录:
 */
@Configuration
@EnableTransactionManagement
public class DataSourceConfig {

    @Autowired
    private DataSourceProperties prop;


    @Bean(initMethod = "init",destroyMethod = "close")
    public DataSource dataSource() throws SQLException{


        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(prop.getDriverClassName());
        dataSource.setUrl(prop.getUrl());
        dataSource.setUsername(prop.getUsername());
        dataSource.setPassword(prop.getPassword());
        dataSource.setInitialSize(prop.getInitialSize());
        dataSource.setMinIdle(prop.getMinIdle());
        dataSource.setMaxActive(prop.getMaxActive());
        dataSource.setMaxWait(prop.getMaxWait());
        dataSource.setTimeBetweenEvictionRunsMillis(prop.getTimeBetweenEvictionRunsMillis());
        dataSource.setMinEvictableIdleTimeMillis(prop.getMinEvictableIdleTimeMillis());
        dataSource.setValidationQuery(prop.getValidationQuery());
        dataSource.setTestWhileIdle(prop.getTestWhileIdle());
        dataSource.setTestOnBorrow(prop.getTestOnBorrow());
        dataSource.setTestOnReturn(prop.getTestOnReturn());
        dataSource.setPoolPreparedStatements(prop.getPoolPreparedStatements());
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(prop.getMaxPoolPreparedStatementPerConnectionSize());
        dataSource.setFilters(prop.getFilters());
        return dataSource;

    }

    @Bean
    public PlatformTransactionManager transactionManager() throws SQLException{
        return new DataSourceTransactionManager(dataSource());
    }
}
