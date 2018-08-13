package schedule.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import schedule.domain.Obj;
import schedule.service.ObjCacheService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * create by lzl ON 2018/08/12
 */
@Service
public class ObjCacheServiceImpl implements ObjCacheService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ObjCacheServiceImpl.class);

    @Cacheable(value = "id",key = "#id")
    @Override
    public List<Obj> getById(Long id) {
        List<Obj> list = new ArrayList<>();
        Obj obj = new Obj();
        obj.setId(id);
        obj.setName("lzl");
        obj.setFlag(true);
        obj.setMyAddress("hz");
        obj.setDate(new Date());
        obj.setTime(LocalDateTime.now());
        list.add(obj);
        return list;
    }

    @Cacheable(value = "obj",key = "#obj.id")
    @Override
    public Obj setObj(Obj obj) {
        return obj;
    }

    @Override
    public void removeById(Long id) {

    }

    @Cacheable(value = "string",key = "#string")
    @Override
    public String setString(String string) {
        return string;
    }

    @Cacheable(value = "string",key = "#string")
    @Override
    public String getString(String string) {
        return string;
    }
}
