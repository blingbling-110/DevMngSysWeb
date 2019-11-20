package com.qzj.devmngsys.controller;

import com.qzj.devmngsys.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class SignOutController {
    @Autowired
    CommonService commonService;

    @RequestMapping("/signOut")
    public ModelAndView signOut(@RequestParam("lang") String language,
                                HttpServletRequest request){
        HttpSession httpSession = request.getSession(false);//防止创建会话
        httpSession.removeAttribute("username");
        httpSession.removeAttribute("isAdmin");
        return commonService.addLang(new ModelAndView("redirect:/"), language);
    }
}
