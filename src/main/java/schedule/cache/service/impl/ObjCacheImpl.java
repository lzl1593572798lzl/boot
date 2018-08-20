package schedule.cache.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import schedule.cache.BaseCache;
import schedule.cache.CacheKey;
import schedule.cache.service.ObjCache;
import schedule.domain.Obj;
import schedule.service.ObjService;
import schedule.util.JsonUtil;

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
