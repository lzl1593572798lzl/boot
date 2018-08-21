package com.lzl.service;

import java.util.List;

/**
 * create by lzl ON 2018/08/20
 */
public interface ListService {

    List<String> list(Integer type);

    void saveList(List<String> stringList);
}
