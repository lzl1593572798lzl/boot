package com.lzl.controller.admin;

import com.lzl.constants.ResponseCode;
import com.lzl.controller.BaseController;
import com.lzl.http.ResponseData;
import com.lzl.myjob.MyThread;
import com.lzl.trigger.MyTrigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

/**
 * create by lzl ON 2018/07/31
 * @author lzl
 */
@RestController
@RequestMapping("/cron")
public class ScheduleController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleController.class);

    private static final Map<String,ScheduledFuture<?>> MAP = new HashMap<>();

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    @GetMapping("/start")
    public ResponseEntity<ResponseData> start(@RequestParam("cron")String cron,
                                              @RequestParam("flag")String flag){
        ScheduledFuture<?> future = MAP.get(flag);
        if(future == null){
            future = threadPoolTaskScheduler.schedule(new MyThread(flag),new MyTrigger(cron));
            MAP.put(flag,future);
            LOGGER.info(" 启动新的定时任务后flag:{} map: {}",flag,MAP);
        }else {
            return renderOk(ResponseCode.ILLEGAL_PARAMETER);
        }
        return renderOk();
    }

    @GetMapping("/stop")
    public ResponseEntity<ResponseData> stop(@RequestParam("flag")String flag){
        ScheduledFuture<?> future = MAP.get(flag);
        if(future == null){
            return renderOk(ResponseCode.ILLEGAL_PARAMETER);
        }else {
            LOGGER.info(" 停止定时任务前 flag:{}  map: {}",flag,MAP);
            future.cancel(true);
            MAP.remove(flag);
            LOGGER.info(" 停止定时任务后 flag:{} map: {}",flag,MAP);
        }
        return renderOk();
    }

    @GetMapping("/restart")
    public ResponseEntity<ResponseData> restart(@RequestParam("cron")String cron,
                                                @RequestParam("flag")String flag){
        ScheduledFuture<?> future = MAP.get(flag);
        if(future == null){
            return renderOk(ResponseCode.ILLEGAL_PARAMETER);
        }else {
            LOGGER.info(" 重启的定时任务前flag:{} map: {}",flag,MAP);
            future.cancel(true);
            future = threadPoolTaskScheduler.schedule(new MyThread(flag),new MyTrigger(cron));
            MAP.put(flag,future);
            LOGGER.info(" 重启定时任务后flag:{}  map: {}",flag,MAP);
        }
        return renderOk();
    }

}
