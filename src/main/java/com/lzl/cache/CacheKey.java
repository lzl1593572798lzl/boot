package com.lzl.cache;

import java.util.concurrent.TimeUnit;

/**
 * 缓存Key的组装方式
 * create by lzl ON 2018/08/16
 */
public enum  CacheKey {

    /**
     * 缓存的有效期 1*hour -> 1小时
     */
    STRING_TEST(1,TimeUnit.HOURS),
    HASH_TEST(1,TimeUnit.DAYS),
    SET_TEST(1,TimeUnit.MINUTES),
    ZSET_TEST(1,TimeUnit.DAYS),
    LIST_TEST(1,TimeUnit.DAYS),

    ;

    CacheKey(Integer number, TimeUnit timeUnit) {
        this.number = number;
        this.timeUnit = timeUnit;
    }

    /**
     * 过期时间的数量
     */
    private Integer number;

    /**
     * key的过期时间单位
     */
    private TimeUnit timeUnit;

    public String key(){
        return this.name();
    }

    public String lockKey(){
        return "LOCK_"+this.name();
    }

    public String key(String suffix){
        return this.name()+"_"+suffix;
    }

    public String lockKey(String suffix){
        return "LOCK_" +this.name()+"_"+suffix;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
    }

}
