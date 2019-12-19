package com.qzj.devmngsys.service;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service
public class LocaleService {
    /**
     * 为模型视图对象添加区域信息参数
     *
     * @param modelAndView 模型视图对象
     * @param language     区域信息参数
     * @return 添加区域信息参数后的模型视图对象
     */
    public ModelAndView addLang(ModelAndView modelAndView, String language) {
        if (language != null && !language.isEmpty())
            modelAndView.addObject("lang", language);
        return modelAndView;
    }
}
