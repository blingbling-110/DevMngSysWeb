package com.qzj.devmngsys.controller;

import com.qzj.devmngsys.service.LocaleService;
import com.qzj.devmngsys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    private LocaleService localeService;

    @Autowired
    private UserService userService;

    @RequestMapping("/user")
    public ModelAndView user(@RequestParam(value = "lang", required = false) String language,
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

    @RequestMapping("/user_update")
    public ModelAndView update(@RequestParam(value = "lang", required = false) String language,
                               @RequestParam(value = "update_id") String id,
                               @RequestParam(value = "update_name") String name,
                               @RequestParam(value = "update_username") String username,
                               @RequestParam(value = "update_pos") String pos,
                               @RequestParam(value = "update_dep") String dep,
                               @RequestParam(value = "update_email") String email,
                               @RequestParam(value = "update_tel") String tel,
                               @RequestParam(value = "update_rem") String rem,
                               @RequestParam(value = "update_isAdmin", required = false) boolean isAdmin,
                               @RequestParam(value = "current_username") String current_username,
                               HttpServletRequest request,
                               HttpServletResponse response) {
        int result = userService.update(id, name, username, pos, dep, email, tel, rem, isAdmin, current_username);
        if (result == -1) {
            Cookie cookie = new Cookie("msg", "Username");
            cookie.setMaxAge(3);
            cookie.setPath(request.getContextPath());
            response.addCookie(cookie);
        }
        ModelAndView modelAndView = new ModelAndView("redirect:user");
        return localeService.addLang(modelAndView, language);
    }

    @RequestMapping("/user_add")
    public ModelAndView add(@RequestParam(value = "lang", required = false) String language,
                            @RequestParam(value = "add_id") String id,
                            @RequestParam(value = "add_name") String name,
                            @RequestParam(value = "add_username") String username,
                            @RequestParam(value = "add_pwd") String pwd,
                            @RequestParam(value = "add_pos") String pos,
                            @RequestParam(value = "add_dep") String dep,
                            @RequestParam(value = "add_email") String email,
                            @RequestParam(value = "add_tel") String tel,
                            @RequestParam(value = "add_rem") String rem,
                            @RequestParam(value = "add_isAdmin", required = false) boolean isAdmin,
                            HttpServletRequest request,
                            HttpServletResponse response) {
        int result = userService.add(id, name, username, pwd, pos, dep, email, tel, rem, isAdmin);
        if (result == -1) {
            Cookie cookie = new Cookie("msg", "EmployeeID");
            cookie.setMaxAge(3);
            cookie.setPath(request.getContextPath());
            response.addCookie(cookie);
        } else if (result == -2) {
            Cookie cookie = new Cookie("msg", "Username");
            cookie.setMaxAge(3);
            cookie.setPath(request.getContextPath());
            response.addCookie(cookie);
        } else if (result == -3) {
            Cookie cookie = new Cookie("msg", "ParseInt");
            cookie.setMaxAge(3);
            cookie.setPath(request.getContextPath());
            response.addCookie(cookie);
        }
        ModelAndView modelAndView = new ModelAndView("redirect:user");
        return localeService.addLang(modelAndView, language);
    }
}
