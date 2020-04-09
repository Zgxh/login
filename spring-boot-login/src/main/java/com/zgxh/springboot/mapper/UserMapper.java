package com.zgxh.springboot.mapper;

import com.zgxh.springboot.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    User selectByUsername(String username);

    String selectRoleByUsername(String username);
}