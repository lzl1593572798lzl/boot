package schedule.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * 缓存操作的基类
 * create by lzl ON 2018/08/16
 */
public abstract class BaseCache {

    @Autowired
    private RedisTemplate redisTemplate;

}
