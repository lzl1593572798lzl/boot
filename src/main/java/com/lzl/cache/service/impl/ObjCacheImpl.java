package com.lzl.cache.service.impl;

import com.lzl.cache.BaseCache;
import com.lzl.cache.CacheKey;
import com.lzl.domain.Obj;
import com.lzl.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lzl.cache.service.ObjCache;
import com.lzl.service.ObjService;

import javax.validation.constraints.NotNull;
import java.util.Map;
import java.util.Optional;

/**
 * create by lzl ON 2018/08/16
 */
@Service
public class ObjCacheImpl extends BaseCache implements ObjCache {

    @Autowired
    private ObjService objService;

    @Override
    public Obj getById(Long id) {
        CacheCallback<Obj> dbToCache = ()->{
            Obj obj = objService.get(id);
            saveCacheObj(obj);
            return obj;
        };
        CacheCallback<Obj> fromCache = ()->{
            Map<String,Object> map = redisTemplate.opsForHash().entries(CacheKey.HASH_TEST.key(id.toString()));
            Obj obj = JsonUtil.convertValue(map,Obj.class);
            return obj;
        };
        Optional<Obj> optional = execute(CacheKey.HASH_TEST,id.toString(),dbToCache,fromCache);
        return optional.orElse(null);
    }

    @Override
    public void saveCache(@NotNull Obj obj) {
        if(!redisTemplate.hasKey(CacheKey.HASH_TEST.key(obj.getId().toString()))){
            getById(obj.getId());
        }else {
            saveCacheObj(obj);
        }
    }

    private void saveCacheObj(@NotNull Obj obj){
        redisTemplate.opsForHash().putAll(CacheKey.HASH_TEST.key(obj.getId().toString()),objToMap(obj));
        System.out.println(redisTemplate.opsForHash().entries(CacheKey.HASH_TEST.key(obj.getId().toString())));
    }

    public Map<String,Object> objToMap(@NotNull Obj obj){
        String objJson = JsonUtil.toJson(obj);
        Map<String,Object> stringObjectMap = JsonUtil.getObjByJsonString(objJson,Map.class);
        return stringObjectMap;
    }

    @Override
    public void updateByField(@NotNull Long id,@NotNull String field, @NotNull Object value) {
        redisTemplate.opsForHash().put(CacheKey.HASH_TEST.key(id.toString()),field,value);
    }
}
