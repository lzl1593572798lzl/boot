package schedule.cache.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import schedule.cache.BaseCache;
import schedule.cache.CacheKey;
import schedule.domain.Obj;
import schedule.service.ObjService;
import schedule.util.JsonUtil;

import javax.validation.constraints.NotNull;
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
            Obj obj = objService.getById(id);
            saveCacheObj(obj);
            return obj;
        };
        CacheCallback<Obj> fromCache = ()->{
            Obj obj =  (Obj)redisTemplate.opsForValue().get(CacheKey.KV_TEST.key(id.toString()));
            return obj;
        };
        Optional<Obj> optional = execute(CacheKey.KV_TEST,id.toString(),dbToCache,fromCache);
        return optional.orElse(null);
    }

    @Override
    public void saveCache(Obj obj) {
        if(null != obj){
            if(!redisTemplate.hasKey(CacheKey.KV_TEST.key(obj.getId().toString()))){
                getById(obj.getId());
            }else {
                saveCacheObj(obj);
            }
        }
    }

    private void saveCacheObj(@NotNull Obj obj){
        redisTemplate.opsForValue().set(CacheKey.KV_TEST.key(obj.getId().toString()),JsonUtil.writeAsString(obj));
    }
}
