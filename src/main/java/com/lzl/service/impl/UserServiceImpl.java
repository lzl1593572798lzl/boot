package com.lzl.service.impl;

import com.lzl.domain.User;
import com.lzl.mapper.UserMapper;
import com.lzl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;

/**
 * @Author: lzl
 * @Date: 2018/08/30 21:40
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer insert(@NotNull User user) {
        return userMapper.insert(user);
    }

    @Override
    public User selectById(@NotNull Long id) {
        return userMapper.selectById(id);
    }
}
