package schedule.cache.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import schedule.cache.BaseCache;
import schedule.cache.CacheKey;
import schedule.cache.service.ListCache;
import schedule.service.ListService;

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
            saveList(CacheKey.LIST_TEST,list);
            return list;
        };
        CacheCallback<List<String>> fromCache = ()->{
          List<String> list = redisTemplate.opsForList().range(CacheKey.LIST_TEST.key(type.toString()),0L,-1L);
          return list;
        };
        Optional<List<String>> optional = execute(CacheKey.LIST_TEST,dbToCache,fromCache);
        return optional.orElse(Collections.EMPTY_LIST);
    }

    @Override
    public void saveList(@NotNull List<String> stringList,@NotNull Integer type) {
        if(!redisTemplate.hasKey(CacheKey.LIST_TEST.key(type.toString()))){
            list(type);
        }else {
            saveList(CacheKey.LIST_TEST,stringList);
        }
    }

    private void  saveList(@NotNull CacheKey cacheKey,@NotNull List<String> list){
        redisTemplate.opsForList().leftPushAll(cacheKey.key(),list);
    }
}
