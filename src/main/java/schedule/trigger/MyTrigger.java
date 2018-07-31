package schedule.trigger;

import org.springframework.scheduling.support.CronTrigger;

import java.util.TimeZone;

/**
 * create by lzl ON 2018/07/31
 */
public class MyTrigger extends CronTrigger {

    public MyTrigger(String expression) {
        super(expression);
    }

    public MyTrigger(String expression, TimeZone timeZone) {
        super(expression, timeZone);
    }

}
