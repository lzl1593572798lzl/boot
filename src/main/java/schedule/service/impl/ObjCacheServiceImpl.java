package schedule.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import schedule.domain.Obj;
import schedule.service.ObjCacheService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * create by lzl ON 2018/08/12
 */
@Service
public class ObjCacheServiceImpl implements ObjCacheService {

    @Autowired
    private RedisTemplate redisTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(ObjCacheServiceImpl.class);

    @Override
    public List<Obj> getById(Long id) {
        if(redisTemplate.hasKey(id)){
           List<Obj> list = (List<Obj>) redisTemplate.opsForValue().get(id);
            System.out.println("从缓存取数据");
           return list;
        }else {
            List<Obj> list = new ArrayList<>();
            Obj obj = new Obj();
            obj.setId(id);
            obj.setDate(new Date());
            obj.setTime(LocalDateTime.now());
            list.add(obj);
            redisTemplate.opsForValue().set(id,list,10, TimeUnit.SECONDS);
            System.out.println("存放缓存数据");
            return list;
        }
    }

    @Override
    public Obj getObjById(Obj obj) {
        return obj;
    }

    @Override
    public void removeById(Long id) {

    }

    @Override
    public String setString(String string) {
        return string;
    }

    @Override
    public String getString(String string) {
        return string;
    }
}
