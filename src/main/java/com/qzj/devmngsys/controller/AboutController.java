package com.qzj.devmngsys.controller;

import com.qzj.devmngsys.service.LocaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AboutController {
    @Autowired
    private LocaleService localeService;

    @RequestMapping("/about")
    public ModelAndView backup(@RequestParam(value = "lang", required = false) String language) {
        return localeService.addLang(new ModelAndView("about"), language);
    }
}
