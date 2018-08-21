package com.lzl.service;

import com.lzl.domain.Obj;

/**
 * create by lzl ON 2018/08/12
 */
public interface ObjService {

    Obj get(Long id);

    void removeById(Long id);

}
