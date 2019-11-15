package com.qzj.devmngsys.service;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service
public class CommonService {
    public ModelAndView addLang(ModelAndView modelAndView, String language) {
        if (language != null && !language.isEmpty())
            modelAndView.addObject("lang", language);
        return modelAndView;
    }
}
