package com.lzl.service;

import com.lzl.config.mybatis.Pg;
import com.lzl.domain.Obj;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * create by lzl ON 2018/08/12
 */
public interface ObjService {

    Obj get(Long id);

    void removeById(Long id);

    Integer insert(Obj obj);

    Pg<Obj> listByPage(Integer pageNum, Integer pageSize,String name);

    List<Obj> listByName(@NotNull String name);

    Integer insertToDb(@NotNull Obj obj);

}
