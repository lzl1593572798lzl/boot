package com.lzl.service;

import com.lzl.domain.User;

import javax.validation.constraints.NotNull;

/**
 * @Author: lzl
 * @Date: 2018/08/30 21:39
 */
public interface UserService {

    Integer insert(@NotNull User user);

    User selectById(@NotNull Long id);
}
