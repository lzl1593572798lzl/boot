package schedule.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import schedule.util.JsonUtil;

/**
 * create by lzl ON 2018/08/12
 */
@Configuration
public class CacheConfig  extends CachingConfigurerSupport{

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        return RedisCacheManager.builder(connectionFactory).build();
    }

    @Bean
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        Jackson2JsonRedisSerializer serializer = new Jackson2JsonRedisSerializer(Object.class);
        serializer.setObjectMapper(JsonUtil.getObjectMapper());
        RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
//        启用默认的序列化设置  这样key、value Hash 对应的序列化 使用的都是同一个序列化类
        redisTemplate.setEnableDefaultSerializer(true);
        redisTemplate.setDefaultSerializer(serializer);
//        显示调用方法 使用上面的设置生效
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

}
