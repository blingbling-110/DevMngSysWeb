package com.qzj.devmngsys.controller;

import com.qzj.devmngsys.service.CommonService;
import com.qzj.devmngsys.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
                              @RequestParam("remember") String remember,
                              HttpSession httpSession,
                              RedirectAttributes redirectAttributes,
                              HttpServletRequest request,
                              HttpServletResponse response) {
        ModelAndView modelAndView;
        if (loginService.login(username, password)) {//验证用户名密码是否正确
            boolean IsAdmin = loginService.isAdmin(username);
            httpSession.setAttribute("username", username);
            httpSession.setAttribute("isAdmin", IsAdmin);
            modelAndView = new ModelAndView("redirect:toDashboard");//重定向，防止表单重复提交
        } else {
            redirectAttributes.addFlashAttribute("error", "用户名或密码错误");
            modelAndView = new ModelAndView("redirect:/");
        }
        if ("on".equals(remember)) {//是否记住用户登录状态，利用cookie实现
            Cookie cookie = new Cookie("username", username);
            cookie.setMaxAge(90 * 24 * 60 * 60);//cookie过期时间设置为三个月
            cookie.setPath(request.getContextPath());
            response.addCookie(cookie);
            cookie = new Cookie("password", password);
            cookie.setMaxAge(90 * 24 * 60 * 60);//cookie过期时间设置为三个月
            cookie.setPath(request.getContextPath());
            response.addCookie(cookie);
        }
        return commonService.addLang(modelAndView, language);
    }
}
