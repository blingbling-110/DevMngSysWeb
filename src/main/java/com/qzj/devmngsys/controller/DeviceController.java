package com.qzj.devmngsys.controller;

import com.qzj.devmngsys.service.DeviceService;
import com.qzj.devmngsys.service.LocaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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

    @RequestMapping("/request")
    public ModelAndView request(@RequestParam(value = "lang", required = false) String language,
                                @RequestParam(value = "devId") String devId,
                                @RequestParam(value = "reqerId") String reqerId) {
        deviceService.sendReq(devId, reqerId);
        ModelAndView modelAndView = new ModelAndView("redirect:device");
        return localeService.addLang(modelAndView, language);
    }

    @RequestMapping("/delete")
    public ModelAndView delete(@RequestParam(value = "lang", required = false) String language,
                               @RequestParam(value = "devId") String devId) {
        deviceService.delete(devId);
        ModelAndView modelAndView = new ModelAndView("redirect:device");
        return localeService.addLang(modelAndView, language);
    }

    @RequestMapping("/update")
    public ModelAndView update(@RequestParam(value = "lang", required = false) String language,
                               @RequestParam(value = "update_id") String devId,
                               @RequestParam(value = "update_name") String devName,
                               @RequestParam(value = "update_des") String devDes,
                               @RequestParam(value = "update_rem") String devRem) {
        deviceService.update(devId, devName, devDes, devRem);
        ModelAndView modelAndView = new ModelAndView("redirect:device");
        return localeService.addLang(modelAndView, language);
    }
}
