package com.lzl.service;

import com.lzl.config.mybatis.Pg;
import com.lzl.domain.Obj;

/**
 * create by lzl ON 2018/08/12
 */
public interface ObjService {

    Obj get(Long id);

    void removeById(Long id);

    Integer insert(Obj obj);

    Pg<Obj> listByPage(Integer pageNum, Integer pageSize,String name);

}
