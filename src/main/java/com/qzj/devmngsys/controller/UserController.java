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
                               @RequestParam(value = "username", required = false) String username,
                               @RequestParam(value = "pos", required = false) String pos,
                               @RequestParam(value = "dep", required = false) String dep,
                               @RequestParam(value = "email", required = false) String email,
                               @RequestParam(value = "tel", required = false) String tel,
                               @RequestParam(value = "remark", required = false) String remark,
                               @RequestParam(value = "isAdmin", required = false) boolean isAdmin,
                               HttpSession httpSession) {
        httpSession.setAttribute("UserInfo", userService.searchUserInfo(id, name, username, pos, dep,
                email, tel, remark, isAdmin));
        ModelAndView modelAndView = new ModelAndView("user");
        return localeService.addLang(modelAndView, language);
    }

    @RequestMapping("/user_delete")
    public ModelAndView delete(@RequestParam(value = "lang", required = false) String language,
                               @RequestParam(value = "id") String id) {
        userService.delete(id);
        ModelAndView modelAndView = new ModelAndView("redirect:user");
        return localeService.addLang(modelAndView, language);
    }
}
