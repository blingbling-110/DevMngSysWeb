package com.qzj.devmngsys.service;

import com.qzj.devmngsys.repository.LoginDao;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private LoginDao loginDao = new LoginDao();

    public String login(String username, String password) {
        if (loginDao.login(username, password))
            return "redirect:/dashboard.html";//重定向，防止表单重复提交
        else
            return "redirect:/";
    }
}
