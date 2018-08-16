package schedule.cache.service;

import schedule.domain.Obj;

/**
 * create by lzl ON 2018/08/16
 */
public interface ObjCache {

    Obj getById(Long id);

    void saveCache(Obj obj);
}
