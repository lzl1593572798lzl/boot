package schedule.service;

import schedule.domain.Obj;

import java.util.List;

/**
 * create by lzl ON 2018/08/12
 */
public interface ObjCacheService {

    List<Obj> getById(Long id);

    Obj getObjById(Obj obj);

    void removeById(Long id);

    String setString(String string);

    String getString(String string);

}
