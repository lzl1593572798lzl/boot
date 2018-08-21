package com.lzl.service.impl;

import com.lzl.service.StringService;
import org.springframework.stereotype.Service;

/**
 * create by lzl ON 2018/08/20
 * @author lzl
 */
@Service
public class StringServiceImpl  implements StringService {

    @Override
    public String get(String name) {
        return name;
    }

    @Override
    public void saveString(String string) {

    }
}
