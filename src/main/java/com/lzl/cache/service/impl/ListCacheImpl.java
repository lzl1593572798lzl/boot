package com.lzl.cache.service.impl;

import com.lzl.cache.BaseCache;
import com.lzl.cache.service.ListCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lzl.cache.CacheKey;
import com.lzl.service.ListService;

import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * create by lzl ON 2018/08/20
 */
@Service
public class ListCacheImpl extends BaseCache implements ListCache {

    @Autowired
    private ListService listService;

    @Override
    public List<String> list(Integer type) {
        CacheCallback<List<String>> dbToCache = ()->{
            List<String> list = listService.list(type);
            saveList(CacheKey.LIST_TEST,list,type);
            return list;
        };
        CacheCallback<List<String>> fromCache = ()->{
          List<String> list = redisTemplate.opsForList().range(CacheKey.LIST_TEST.key(type.toString()),0L,-1L);
          return list;
        };
        Optional<List<String>> optional = execute(CacheKey.LIST_TEST,type.toString(),dbToCache,fromCache);
        return optional.orElse(Collections.EMPTY_LIST);
    }

    @Override
    public void saveList(@NotNull List<String> stringList,@NotNull Integer type) {
        if(!redisTemplate.hasKey(CacheKey.LIST_TEST.lockKey(type.toString()))){
            list(type);
        }else {
            saveList(CacheKey.LIST_TEST,stringList,type);
        }
    }

    private void  saveList(@NotNull CacheKey cacheKey,@NotNull List<String> list,@NotNull Integer type){
        redisTemplate.opsForList().leftPushAll(cacheKey.key(type.toString()),list);
    }
}
