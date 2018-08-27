package com.lzl.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lzl.config.mybatis.Pg;
import com.lzl.domain.Obj;
import com.lzl.mapper.ObjMapper;
import com.lzl.service.ObjService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;

/**
 * create by lzl ON 2018/08/12
 */
@Service
public class ObjServiceImpl implements ObjService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ObjServiceImpl.class);

    @Autowired
    private ObjMapper objMapper;

    @Override
    public Obj get(Long id) {
        return objMapper.getById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer insert(Obj obj) {
        return objMapper.insert(obj);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void removeById(Long id) {
        objMapper.updateDeletedById(id,true);
//        throw  new RuntimeException();
    }

    @Override
    public Pg<Obj> listByPage(@NotNull Integer pageNum, @NotNull Integer pageSize,@NotNull String name) {
        PageInfo<Obj> pageInfo = PageHelper.startPage(pageNum,pageSize).doSelectPageInfo(()->{
            objMapper.list(name);
        });
        return Pg.ofPageInfo(pageInfo);
    }
}
