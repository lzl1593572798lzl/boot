package schedule.cache;

import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * 缓存操作的基类
 * create by lzl ON 2018/08/16
 */
public abstract class BaseCache {

    @Autowired
    protected RedisTemplate redisTemplate;

    protected final  <T> Optional<T>  execute(CacheKey  cacheKey, String suffix, CacheCallback<T> dbToCache, CacheCallback<T> fromCache){
        if(null == cacheKey || null == dbToCache || null == fromCache){
            return Optional.empty();
        }
        String lockKey = Strings.isNullOrEmpty(suffix) ? cacheKey.lockKey() : cacheKey.lockKey(suffix);
        T t = null;
//        判断此前lock是否存在 存在直接调用缓存 不存在 调用db
        Boolean lockFlag = redisTemplate.opsForValue().setIfAbsent(lockKey,"");
        if(lockFlag){
//            表明之前不存在lockKey
            redisTemplate.expire(lockKey,60, TimeUnit.SECONDS);
           t =  dbToCache.getData();
            setTTL(cacheKey,suffix);
        }else {
//            之前存在lockKey
            if(!redisTemplate.hasKey(cacheKey.key(suffix))){
                t = null;
            }else {
                t = fromCache.getData();
                setTTL(cacheKey,suffix);
            }
        }
        return Optional.ofNullable(t);
    }

    protected final  <T> Optional<T> execute(CacheKey  cacheKey,CacheCallback<T> dbToCache,CacheCallback<T> fromCache){
        return execute(cacheKey,"",dbToCache,fromCache);
    }

    /**
     * 设置lockKey和key的过期时间
     * 当非首次调用时会自动延长过期时间
     * @param cacheKey
     * @param suffix
     */
    private void setTTL(CacheKey cacheKey,String suffix){
        if(!Strings.isNullOrEmpty(suffix)){
            String lockKey = cacheKey.lockKey(suffix);
            String key = cacheKey.key(suffix);
            long lockKeyTTL =  redisTemplate.getExpire(lockKey);
            long keyTTL =  redisTemplate.getExpire(key);
            redisTemplate.expire(lockKey,lockKeyTTL,TimeUnit.SECONDS);
            redisTemplate.expire(key,keyTTL,TimeUnit.SECONDS);
        }else {
            long lockKeyTTL =  redisTemplate.getExpire(cacheKey.lockKey());
            redisTemplate.expire(cacheKey.lockKey(),lockKeyTTL,TimeUnit.SECONDS);
        }

    }

    protected interface  CacheCallback<T>{
        T getData();
    }

}
