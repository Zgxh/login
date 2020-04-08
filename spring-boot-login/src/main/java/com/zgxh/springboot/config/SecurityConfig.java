package com.zgxh.springboot.config;

import com.zgxh.springboot.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * spring security 的配置类
 *
 * @author Yu Yang
 * @create 2020-04-08 19:28
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/static/**").permitAll().anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login") // 未登录时重定向的页面
                .permitAll().successHandler(myLoginSuccessHandler()) // 登录成功时的处理器
                .and()
                .logout().permitAll().invalidateHttpSession(true).deleteCookies("JSESSIONID")
                .logoutSuccessHandler()
    }

    @Bean
    public AuthenticationSuccessHandler myLoginSuccessHandler() { // 获取登入成功的handler，采用匿名内部类的形式生成
        return new SavedRequestAwareAuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
                User user = (User) authentication.getPrincipal();
                logger.info(user.getUsername() + ", Login Success!");
                super.onAuthenticationSuccess(request, response, authentication);
            }
        };
    }

    @Bean
    public LogoutSuccessHandler myLogoutSuccessHandler() { // 登出成功的Handler
        return new LogoutSuccessHandler() {
            @Override
            public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                try {

                }
            }
        }
    }
}
