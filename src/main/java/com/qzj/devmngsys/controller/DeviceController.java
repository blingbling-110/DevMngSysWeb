package com.qzj.devmngsys.controller;

import com.qzj.devmngsys.service.DeviceService;
import com.qzj.devmngsys.service.LocaleService;
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
public class DeviceController {
    @Autowired
    private LocaleService localeService;

    @Autowired
    private DeviceService deviceService;

    @RequestMapping("/device")
    public ModelAndView device(@RequestParam(value = "lang", required = false) String language,
                               @RequestParam(value = "id", required = false) String id,
                               @RequestParam(value = "name", required = false) String name,
                               @RequestParam(value = "status", required = false) String status,
                               @RequestParam(value = "des", required = false) String des,
                               @RequestParam(value = "remark", required = false) String remark,
                               HttpSession httpSession) {
        httpSession.setAttribute("DevInfo", deviceService.searchDevInfo(id, name, status, des, remark));
        ModelAndView modelAndView = new ModelAndView("device");
        return localeService.addLang(modelAndView, language);
    }

    @RequestMapping("/device_request")
    public ModelAndView request(@RequestParam(value = "lang", required = false) String language,
                                @RequestParam(value = "devId") String devId,
                                @RequestParam(value = "reqerId") String reqerId) {
        deviceService.sendReq(devId, reqerId);
        ModelAndView modelAndView = new ModelAndView("redirect:device");
        return localeService.addLang(modelAndView, language);
    }

    @RequestMapping("/device_delete")
    public ModelAndView delete(@RequestParam(value = "lang", required = false) String language,
                               @RequestParam(value = "devId") String devId) {
        deviceService.delete(devId);
        ModelAndView modelAndView = new ModelAndView("redirect:device");
        return localeService.addLang(modelAndView, language);
    }

    @RequestMapping("/device_update")
    public ModelAndView update(@RequestParam(value = "lang", required = false) String language,
                               @RequestParam(value = "update_id") String devId,
                               @RequestParam(value = "update_name") String devName,
                               @RequestParam(value = "update_des") String devDes,
                               @RequestParam(value = "update_rem") String devRem) {
        deviceService.update(devId, devName, devDes, devRem);
        ModelAndView modelAndView = new ModelAndView("redirect:device");
        return localeService.addLang(modelAndView, language);
    }

    @RequestMapping("/device_add")
    public ModelAndView add(@RequestParam(value = "lang", required = false) String language,
                            @RequestParam(value = "add_id") String devId,
                            @RequestParam(value = "add_name") String devName,
                            @RequestParam(value = "add_status") String devSta,
                            @RequestParam(value = "add_des") String devDes,
                            @RequestParam(value = "add_rem") String devRem,
                            HttpServletRequest request,
                            HttpServletResponse response) {
        int result = deviceService.add(devId, devName, devSta, devDes, devRem);
        if (result == -1) {
            Cookie cookie = new Cookie("msg", "DevId");
            cookie.setMaxAge(3);
            cookie.setPath(request.getContextPath());
            response.addCookie(cookie);
        } else if (result == -2) {
            Cookie cookie = new Cookie("msg", "DevSta");
            cookie.setMaxAge(3);
            cookie.setPath(request.getContextPath());
            response.addCookie(cookie);
        }
        ModelAndView modelAndView = new ModelAndView("redirect:device");
        return localeService.addLang(modelAndView, language);
    }
}
