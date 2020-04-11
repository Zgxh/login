package com.zgxh.springboot.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

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

    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class); // 后台控制台打印日志

    @Autowired
    private UserDetailsService databaseUserDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception { // 主策略配置
        http.csrf().disable()
                .authorizeRequests()
                // .antMatchers("/templates/**").permitAll() // 不拦截这些路径
                .anyRequest().authenticated()
                .and()
                .formLogin() // 使用form表单进行登录
                .loginPage("/login").permitAll() // 未登录时重定向的URL，不拦截
                .successHandler(myLoginSuccessHandler()) // 登录成功时的处理器
                .failureHandler(myLoginFailureHandler())
                .loginProcessingUrl("/user/login") // 让spring security来处理该路径进行登录验证，是表单提交的URL
                ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(databaseUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**"); // 防止spring security对静态资源的拦截
    }

    public PasswordEncoder passwordEncoder() { // 密码加密工具
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationSuccessHandler myLoginSuccessHandler() { // 获取login成功的handler，采用匿名内部类的形式生成
        return new SavedRequestAwareAuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                                Authentication authentication) throws ServletException, IOException {
                logger.info( authentication.getName() + ", Login Successfully!");
                response.sendRedirect("/index/" + authentication.getName());
            }
        };
    }

    @Bean
    public AuthenticationFailureHandler myLoginFailureHandler() { // 获取login失败时的handler
        return new SimpleUrlAuthenticationFailureHandler() {
            @Override
            public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                                AuthenticationException exception) throws IOException, ServletException {
                logger.info( "Login Failure!");
            }
        };
    }

}
