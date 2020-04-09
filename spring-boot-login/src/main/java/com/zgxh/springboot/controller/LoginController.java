package com.zgxh.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Yu Yang
 * @create 2020-04-08 19:06
 */
@Controller
public class LoginController {

    @GetMapping(path = "/login") // 报错Circular view path 是因为页面名和path相同，导致循环请求映射到该方法上，解决：把view名改成了userlogin
    public String login() {
        return "userlogin"; // 跳转页面出现404，是因为忘了引入thymeleaf模板引擎，导致模板没有找到，引入后默认到/templates下找页面
    }

    @ResponseBody
    @RequestMapping("/index/{username}") // login成功时重定向的URL
    public String index(@PathVariable String username) {
        return "欢迎回来! " + username;
    }
}
