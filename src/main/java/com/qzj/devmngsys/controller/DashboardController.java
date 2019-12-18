package com.qzj.devmngsys.controller;

import com.qzj.devmngsys.entities.TbUserInfo;
import com.qzj.devmngsys.service.CommonService;
import com.qzj.devmngsys.service.DashboardService;
import com.qzj.devmngsys.service.LocaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class DashboardController {
    @Autowired
    private LocaleService localeService;

    @Autowired
    private DashboardService dashboardService;

    @Autowired
    private CommonService commonService;

    @RequestMapping("/toDashboard")
    public ModelAndView dashboard(@RequestParam(value = "lang", required = false) String language,
                                  HttpSession httpSession){
        TbUserInfo tbUserInfo = (TbUserInfo)httpSession.getAttribute("tbUserInfo");
        httpSession.setAttribute("myDevInfo", dashboardService.getMyDev(tbUserInfo.getId()));
        ModelAndView modelAndView = new ModelAndView("redirect:dashboard");
        return localeService.addLang(modelAndView, language);
    }

    @RequestMapping("/agree")
    public ModelAndView agree(@RequestParam(value = "lang", required = false) String language,
                              @RequestParam(value = "devId") String devId,
                              @RequestParam(value = "reqerId") String reqerId) {
        commonService.borrow(devId, reqerId, "设备转移");
        ModelAndView modelAndView = new ModelAndView("redirect:dashboard");
        return localeService.addLang(modelAndView, language);
    }
}
