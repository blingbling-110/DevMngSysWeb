package com.qzj.devmngsys.controller;

import com.qzj.devmngsys.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DashboardController {
    @Autowired
    private CommonService commonService;

    @RequestMapping("/toDashboard")
    public ModelAndView dashboard(@RequestParam(value = "lang", required = false) String language){
        ModelAndView modelAndView = new ModelAndView("redirect:dashboard");
        return commonService.addLang(modelAndView, language);
    }
}
