package com.qzj.devmngsys.controller;

import com.qzj.devmngsys.service.LocaleService;
import com.qzj.devmngsys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    private LocaleService localeService;

    @Autowired
    private UserService userService;

    @RequestMapping("/user")
    public ModelAndView device(@RequestParam(value = "lang", required = false) String language,
                               @RequestParam(value = "id", required = false) String id,
                               @RequestParam(value = "name", required = false) String name,
                               @RequestParam(value = "status", required = false) String status,
                               @RequestParam(value = "des", required = false) String des,
                               @RequestParam(value = "remark", required = false) String remark,
                               HttpSession httpSession) {
        httpSession.setAttribute("UserInfo", userService.searchUserInfo(id, name, status, des, remark));
        ModelAndView modelAndView = new ModelAndView("user");
        return localeService.addLang(modelAndView, language);
    }
}
