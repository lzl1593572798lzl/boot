package schedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * create by lzl ON 2018/07/31
 */
@SpringBootApplication
@EnableScheduling
public class ScheduleAppliaction {

    public static void main(String[] args) {
        SpringApplication.run(ScheduleAppliaction.class,args);

    }
}
