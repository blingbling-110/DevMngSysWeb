package com.qzj.devmngsys.controller;

import com.qzj.devmngsys.service.CommonService;
import com.qzj.devmngsys.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private CommonService commonService;

    @PostMapping("/login")
    public ModelAndView login(@RequestParam("username") String username,
                              @RequestParam("password") String password,
                              @RequestParam("lang") String language,
                              HttpSession httpSession,
                              RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView;
        if (loginService.login(username, password)) {
            boolean IsAdmin = loginService.isAdmin(username);
            httpSession.setAttribute("username", username);
            httpSession.setAttribute("isAdmin", IsAdmin);
            modelAndView = new ModelAndView("redirect:toDashboard");//重定向，防止表单重复提交
        } else {
            redirectAttributes.addFlashAttribute("error", "用户名或密码错误");
            modelAndView = new ModelAndView("redirect:/");
        }
        return commonService.addLang(modelAndView, language);
    }
}
