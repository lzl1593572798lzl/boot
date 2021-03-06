package com.lzl.config;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Component;

/**
 * 定时任务配置类
 * create by lzl ON 2018/07/31
 * @author lzl
 */
@Component
public class ScheduleConfig implements SchedulingConfigurer {

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.setScheduler(threadPoolTaskScheduler());
    }

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler(){
        ThreadPoolTaskScheduler poolTaskScheduler = new ThreadPoolTaskScheduler();
        poolTaskScheduler.setPoolSize(10);
        poolTaskScheduler.setThreadNamePrefix("poolTaskScheduler-");
        poolTaskScheduler.setAwaitTerminationSeconds(60);
        poolTaskScheduler.initialize();
        return poolTaskScheduler;
    }

}
