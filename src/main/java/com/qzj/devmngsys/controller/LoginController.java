package com.qzj.devmngsys.controller;

import com.qzj.devmngsys.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String, String> map) {
        if (loginService.login(username, password))
            return "redirect:/dashboard.html";//重定向，防止表单重复提交
        else
        {
            map.put("error", "用户名或密码错误");
            return "/index.html";
        }
    }
}
