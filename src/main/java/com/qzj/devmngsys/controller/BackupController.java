package com.qzj.devmngsys.controller;

import com.qzj.devmngsys.service.LocaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BackupController {
    @Autowired
    private LocaleService localeService;

    @RequestMapping("/backup")
    public ModelAndView device(@RequestParam(value = "lang", required = false) String language) {
        ModelAndView modelAndView = new ModelAndView("backup");
        return localeService.addLang(modelAndView, language);
    }
}
