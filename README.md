项目目的：
   1.quartz 启动时加在数据中的信息在schedule中添加job 和Trigger  使用@PostConstruct注解在Register方法上实现
   
        功能实现：
            1.添加ScheduleMate的表
            2.在QuartConfig类中添加@PostConstruct注解在Register方法上
            3.Register方法中查询ScheduleMate中的数据，并在Schedule中添加Job和Trigger
   2.运行时添加Job和Trigger可，和删除Trigger
        
        功能实现：
            1.应该是在运行时添加或删除Trigger。因为Job.class是在项目启动之前就已经创建了的。
            2.先见JobDetail添加到Schedule中，运行时添加对应的Trigger
            3.也可以删除定义的Trigger
            4.注意一个Job对应多个Trigger的方式
            5.Job只能添加一次
            6.Trigger执行完成后对应的信息会删除
            7.在删除Trigger时，可以先暂停Trigger在删除Trigger
   
   4.Schedule 的信息的保存  
         
         使用org.quartz.jobStore.class=org.quartz.impl.jdbcjobstore.JobStoreTX实现Schedule信息的baocun
         可以解决服务宕机重启后接着宕机前的运行时环境信息继续执行
        
        
   5.schedule missfire 策略验证
      