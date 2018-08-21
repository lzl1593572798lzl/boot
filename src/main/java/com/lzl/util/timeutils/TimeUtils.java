package com.lzl.util.timeutils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 处理时间的工具类
 * create by lzl ON 2018/08/01
 */
public class TimeUtils {

    public static final String YYYY_MM_DD = "yyyy-MM-dd";

    public static final String Y_M_D_H_M_S = "yyyy-MM-dd HH:mm:ss";

    /**
     * 默认东八区
     * 北京时间
     */
    public static final ZoneOffset ZONE_OFFSET = ZoneOffset.ofHours(8);

    private TimeUtils(){

    }

    /**
     * 到毫秒级
     * 13位
     * time -> timestamp
     * @param time
     * @return
     */
    public static Long timeToTimestampForMillSecond(LocalDateTime time){
        return timeToTimestampForMillSecond(time,ZONE_OFFSET);
    }

    /**
     * 到秒级 10位
     * @param time
     * @return
     */
    public static Long timeToTimestampForSecond(LocalDateTime time){
        return timeToTimestampForSecond(time,ZONE_OFFSET);
    }

    public static Long timeToTimestampForMillSecond(LocalDateTime time,ZoneOffset zoneOffset){
        return time.toInstant(zoneOffset).toEpochMilli();
    }

    public static Long timeToTimestampForSecond(LocalDateTime time,ZoneOffset offset){
        return time.toEpochSecond(offset);
    }

    /**
     * timestamp -> time
     * @param time
     * @return
     */
    public static LocalDateTime timeStampToTime(Long time){
        return LocalDateTime.ofInstant(new Date(time).toInstant(), ZoneId.of("Asia/Shanghai"));
    }

    /**
     * yyyy-MM-dd
     * @param time
     * @return
     */
    public static String timeFormat(LocalDateTime time){
        return timeFormat(time,YYYY_MM_DD);
    }

    /**
     * 按照指定格式格式化时间
     * @param time
     * @param formatPattrn
     * @return
     */
    public static String timeFormat(LocalDateTime time,String formatPattrn){
        return time.format(DateTimeFormatter.ofPattern(formatPattrn));
    }

}
