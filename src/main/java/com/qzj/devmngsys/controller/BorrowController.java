package com.qzj.devmngsys.controller;

import com.qzj.devmngsys.service.BorrowService;
import com.qzj.devmngsys.service.CommonService;
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
public class BorrowController {
    @Autowired
    private LocaleService localeService;

    @Autowired
    private BorrowService borrowService;

    @Autowired
    private CommonService commonService;

    @RequestMapping("/borrow")
    public ModelAndView borrow(@RequestParam(value = "lang", required = false) String language,
                               @RequestParam(value = "id", required = false) String id,
                               @RequestParam(value = "devId", required = false) String devId,
                               @RequestParam(value = "brwerId", required = false) String brwerId,
                               @RequestParam(value = "date", required = false) String date,
                               @RequestParam(value = "remark", required = false) String remark,
                               HttpSession httpSession) {
        httpSession.setAttribute("BrwInfo", borrowService.searchBrwInfo(id, devId, brwerId, date, remark));
        ModelAndView modelAndView = new ModelAndView("borrow");
        return localeService.addLang(modelAndView, language);
    }

    @RequestMapping("/borrow_add")
    public ModelAndView add(@RequestParam(value = "lang", required = false) String language,
                            @RequestParam(value = "device_id") String devId,
                            @RequestParam(value = "brwerId") String brwerId,
                            @RequestParam(value = "add_rem") String rem,
                            HttpServletRequest request,
                            HttpServletResponse response) {
        int result = commonService.borrow(devId, brwerId, rem);
        if (result == -1) {
            Cookie cookie = new Cookie("msg", "BorrowFailed");
            cookie.setMaxAge(3);
            cookie.setPath(request.getContextPath());
            response.addCookie(cookie);
        } else if (result == -2) {
            Cookie cookie = new Cookie("msg", "DeviceBorrowed");
            cookie.setMaxAge(3);
            cookie.setPath(request.getContextPath());
            response.addCookie(cookie);
        } else if (result == -3) {
            Cookie cookie = new Cookie("msg", "DeviceIdNotExist");
            cookie.setMaxAge(3);
            cookie.setPath(request.getContextPath());
            response.addCookie(cookie);
        } else if (result == -4) {
            Cookie cookie = new Cookie("msg", "ParseInt");
            cookie.setMaxAge(3);
            cookie.setPath(request.getContextPath());
            response.addCookie(cookie);
        } else if (result == -5) {
            Cookie cookie = new Cookie("msg", "BorrowerIdNotExist");
            cookie.setMaxAge(3);
            cookie.setPath(request.getContextPath());
            response.addCookie(cookie);
        }
        ModelAndView modelAndView = new ModelAndView("redirect:borrow");
        return localeService.addLang(modelAndView, language);
    }
}
