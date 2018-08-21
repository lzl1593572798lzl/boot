package com.lzl.cache.service;

import com.lzl.domain.Obj;

/**
 * create by lzl ON 2018/08/16
 */
public interface ObjCache {

    /**
     * 获取数据
     * @param id
     * @return
     */
    Obj getById(Long id);

    /**
     * 保存数据
     * @param obj
     */
    void saveCache(Obj obj);

    /**
     * 根据属性更新属性值
     * @param id
     * @param field
     * @param value
     */
    void updateByField(Long id,String field,Object value);
}
