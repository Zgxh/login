package com.zgxh.springboot.service.impl;

import com.zgxh.springboot.mapper.UserMapper;
import com.zgxh.springboot.model.User;
import com.zgxh.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Yu Yang
 * @create 2020-04-09 16:53
 */
@Service
@Transactional
public class DatabaseUserDetailsService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user =  userMapper.selectByUsername(s);
        if (user == null) {
            throw new UsernameNotFoundException("user + " + s + "not found.");
        }
        String role = userMapper.selectRoleByUsername(s);
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role));
        return new org.springframework.security.core
                .userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}
