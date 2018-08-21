package com.lzl.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.lzl.domain.Obj;
import com.lzl.service.ObjService;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * create by lzl ON 2018/08/12
 */
@Service
public class ObjServiceImpl implements ObjService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ObjServiceImpl.class);

    @Override
    public Obj get(Long id) {
        Obj obj = new Obj();
        obj.setTime(LocalDateTime.now());
        obj.setFlag(true);
        obj.setDate(new Date());
        obj.setId(id);
        obj.setMyAddress("hang zhou");
        obj.setAge(23);
        obj.setScore(23D);
        obj.setSalary(2434L);
        return obj;
    }

    @Override
    public void removeById(Long id) {

    }

}
