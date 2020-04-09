package com.zgxh.springboot.security;

import com.zgxh.springboot.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * UserDetails实现类
 *
 * @author Yu Yang
 * @create 2020-04-08 20:57
 */
public class SecurityUser extends User implements UserDetails {

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        String username = getUsername();
        if (username != null) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(username);
            authorities.add(grantedAuthority);
        }
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() { // 账号是否过期
        return true;
    }

    @Override
    public boolean isAccountNonLocked() { // 账号是否未被锁
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() { // 密码是否未过期
        return true;
    }

    @Override
    public boolean isEnabled() { // 是否已激活
        return true;
    }
}
