package com.zgxh.springboot.service.impl;

import com.zgxh.springboot.mapper.UserMapper;
import com.zgxh.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Yu Yang
 * @create 2020-04-09 20:10
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public String selectRoleByUsername(String username) {
        return userMapper.selectRoleByUsername(username);
    }
}
