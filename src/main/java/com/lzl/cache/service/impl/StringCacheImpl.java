package com.lzl.cache.service.impl;

import com.lzl.cache.BaseCache;
import com.lzl.cache.CacheKey;
import com.lzl.cache.service.StringCache;
import com.lzl.service.StringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Optional;

/**
 * create by lzl ON 2018/08/20
 */
@Service
public class StringCacheImpl extends BaseCache implements StringCache {

    @Autowired
    private StringService stringService;

    @Override
    public String get(String name) {
        CacheCallback<String> dbToCache = () ->{
            String str = stringService.get(name);
            saveCache(CacheKey.STRING_TEST,name);
            return str;
        };
        CacheCallback<String> fromCache = () ->{
            String str = (String) redisTemplate.opsForValue().get(CacheKey.STRING_TEST.key(name));
            return str;
        };
        Optional<String> optional = execute(CacheKey.STRING_TEST,name,dbToCache,fromCache);
        return optional.orElse(null);
    }

    @Override
    public void saveCache(@NotNull String name) {
        if(!redisTemplate.hasKey(CacheKey.STRING_TEST.lockKey(name))){
            get(name);
        }else {
            saveCache(CacheKey.STRING_TEST,name);
        }
    }

    private void saveCache(@NotNull CacheKey cacheKey,@NotNull String name){
        redisTemplate.opsForValue().set(cacheKey.key(name),name);
    }
}
