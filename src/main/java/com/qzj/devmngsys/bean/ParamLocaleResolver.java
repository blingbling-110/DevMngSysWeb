package com.qzj.devmngsys.bean;

import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class ParamLocaleResolver implements LocaleResolver {
    @Override
    public Locale resolveLocale(HttpServletRequest httpServletRequest) {
        Locale locale = Locale.getDefault();//获取JVM默认区域信息
        String lang = httpServletRequest.getParameter("lang");//获取URL中的lang参数
        if (lang != null && !lang.isEmpty())
            locale = new Locale(lang.split("_")[0], lang.split("_")[1]);
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }
}
