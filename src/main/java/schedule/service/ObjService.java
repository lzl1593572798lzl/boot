package schedule.service;

import schedule.domain.Obj;

/**
 * create by lzl ON 2018/08/12
 */
public interface ObjService {

    Obj getById(Long id);

    void removeById(Long id);

    String setString(String string);

    String getString(String string);

}
