package com.qzj.devmngsys.controller;

import com.qzj.devmngsys.service.LocaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class SignOutController {
    @Autowired
    LocaleService localeService;

    @RequestMapping("/signOut")
    public ModelAndView signOut(@RequestParam("lang") String language,
                                HttpServletRequest request,
                                HttpServletResponse response){
        HttpSession httpSession = request.getSession(false);//防止创建会话
        httpSession.removeAttribute("username");
        httpSession.removeAttribute("isAdmin");
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (StringUtils.equals(cookie.getName(), "username")) {
                    cookie.setMaxAge(0);//使cookie过期
                    cookie.setPath(request.getContextPath());
                    response.addCookie(cookie);
                } else if (StringUtils.equals(cookie.getName(), "password")) {
                    cookie.setMaxAge(0);
                    cookie.setPath(request.getContextPath());
                    response.addCookie(cookie);
                }
            }
        }
        return localeService.addLang(new ModelAndView("redirect:/"), language);
    }
}
