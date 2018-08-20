package schedule.cache;

import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * 缓存操作的基类
 * create by lzl ON 2018/08/16
 */
public abstract class BaseCache {

    @Autowired
    protected RedisTemplate redisTemplate;

    protected final  <T> Optional<T>  execute(@NotNull CacheKey  cacheKey,@NotNull String suffix,
                                              @NotNull CacheCallback<T> dbToCache,@NotNull CacheCallback<T> fromCache){
        if(null == cacheKey || null == dbToCache || null == fromCache){
            return Optional.empty();
        }
        String lockKey = Strings.isNullOrEmpty(suffix) ? cacheKey.lockKey() : cacheKey.lockKey(suffix);
//        判断此前lock是否存在 存在直接调用缓存 不存在 调用db
        Boolean lockFlag = redisTemplate.opsForValue().setIfAbsent(lockKey,"");
        if(lockFlag){
//            表明之前不存在lockKey
            redisTemplate.expire(lockKey,60, TimeUnit.SECONDS);
           T t =  dbToCache.getData();
            setTTL(cacheKey,suffix);
            return Optional.ofNullable(t);
        }else {
//            之前存在lockKey
            if(!redisTemplate.hasKey(cacheKey.key(suffix))){
                return Optional.ofNullable(null);
            }else {
               T t = fromCache.getData();
               setTTL(cacheKey,suffix);
               return Optional.ofNullable(t);
            }
        }
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
    private void setTTL(@NotNull CacheKey cacheKey,@NotNull String suffix){
        if(!Strings.isNullOrEmpty(suffix)){
            redisTemplate.expire(cacheKey.lockKey(suffix),cacheKey.getNumber() * 2,cacheKey.getTimeUnit());
            redisTemplate.expire(cacheKey.key(suffix),cacheKey.getNumber() * 2,cacheKey.getTimeUnit());
        }else {
            redisTemplate.expire(cacheKey.lockKey(),cacheKey.getNumber() * 2,cacheKey.getTimeUnit());
        }
    }

    protected interface  CacheCallback<T>{
        T getData();
    }

}
