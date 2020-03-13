package com.qzj.devmngsys.controller;

import com.qzj.devmngsys.service.LocaleService;
import com.qzj.devmngsys.service.ReturnService;
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
public class ReturnController {
    @Autowired
    private LocaleService localeService;

    @Autowired
    private ReturnService returnService;

    @RequestMapping("/return")
    public ModelAndView device(@RequestParam(value = "lang", required = false) String language,
                               @RequestParam(value = "id", required = false) String id,
                               @RequestParam(value = "devId", required = false) String devId,
                               @RequestParam(value = "rtnerId", required = false) String rtnerId,
                               @RequestParam(value = "date", required = false) String date,
                               @RequestParam(value = "remark", required = false) String remark,
                               HttpSession httpSession) {
        httpSession.setAttribute("RtnInfo", returnService.searchRtnInfo(id, devId, rtnerId, date, remark));
        ModelAndView modelAndView = new ModelAndView("return");
        return localeService.addLang(modelAndView, language);
    }

    @RequestMapping("/return_add")
    public ModelAndView add(@RequestParam(value = "lang", required = false) String language,
                            @RequestParam(value = "device_id") String devId,
                            @RequestParam(value = "rtnerId") String rtnerId,
                            @RequestParam(value = "add_rem") String rem,
                            HttpServletRequest request,
                            HttpServletResponse response) {
        int result = returnService.returnDev(devId, rtnerId, rem);
        if (result == -1) {
            Cookie cookie = new Cookie("msg", "ReturnFailed");
            cookie.setMaxAge(5);
            cookie.setPath(request.getContextPath());
            response.addCookie(cookie);
        } else if (result == -2) {
            Cookie cookie = new Cookie("msg", "DeviceReturned");
            cookie.setMaxAge(5);
            cookie.setPath(request.getContextPath());
            response.addCookie(cookie);
        } else if (result == -3) {
            Cookie cookie = new Cookie("msg", "DeviceIdNotExist");
            cookie.setMaxAge(5);
            cookie.setPath(request.getContextPath());
            response.addCookie(cookie);
        } else if (result == -4) {
            Cookie cookie = new Cookie("msg", "ReturnerIdError");
            cookie.setMaxAge(5);
            cookie.setPath(request.getContextPath());
            response.addCookie(cookie);
        } else if (result == -5) {
            Cookie cookie = new Cookie("msg", "ReturnerIdNotExist");
            cookie.setMaxAge(5);
            cookie.setPath(request.getContextPath());
            response.addCookie(cookie);
        }
        ModelAndView modelAndView = new ModelAndView("redirect:return");
        return localeService.addLang(modelAndView, language);
    }
}
