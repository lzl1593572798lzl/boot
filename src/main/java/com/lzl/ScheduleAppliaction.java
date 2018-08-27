package com.lzl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * create by lzl ON 2018/07/31
 * @author lzl
 */
@SpringBootApplication
@EnableScheduling
@EnableWebMvc
@EnableCaching
public class ScheduleAppliaction {

    public static void main(String[] args) {
        SpringApplication.run(ScheduleAppliaction.class,args);

    }
}