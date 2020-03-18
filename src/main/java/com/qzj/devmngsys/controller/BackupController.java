package com.qzj.devmngsys.controller;

import com.qzj.devmngsys.service.BackupService;
import com.qzj.devmngsys.service.LocaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class BackupController {
    @Autowired
    private LocaleService localeService;

    @Autowired
    private BackupService backupService;

    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String pwd;
    @Value("${spring.datasource.url}")
    private String url;

    @RequestMapping("/backup")
    public ModelAndView backup(@RequestParam(value = "lang", required = false) String language,
                               HttpSession httpSession) {
        httpSession.setAttribute("backupFiles", backupService.search());
        ModelAndView modelAndView = new ModelAndView("backup");
        return localeService.addLang(modelAndView, language);
    }

    @RequestMapping("/do_backup")
    public ModelAndView doBackup(@RequestParam(value = "lang", required = false) String language,
                                 HttpServletRequest request,
                                 HttpServletResponse response){
        String result = backupService.doBackup(username, pwd, url);
        if (result.equals("error")) {
            Cookie cookie = new Cookie("msg", "backupFailed");
            cookie.setMaxAge(3);
            cookie.setPath(request.getContextPath());
            response.addCookie(cookie);
        }else {
            Cookie cookie = new Cookie("msg", result);
            cookie.setMaxAge(3);
            cookie.setPath(request.getContextPath());
            response.addCookie(cookie);
        }
        ModelAndView modelAndView = new ModelAndView("redirect:backup");
        try {
            Thread.sleep(1000);//延时一秒钟
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return localeService.addLang(modelAndView, language);
    }

    @RequestMapping("/restore")
    public ModelAndView restore(@RequestParam(value = "lang", required = false) String language,
                                @RequestParam(value = "fileName") String fileName,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        int result = backupService.restore(username, pwd, url, fileName);
        if (result == -1) {
            Cookie cookie = new Cookie("msg", "restoreFailed");
            cookie.setMaxAge(3);
            cookie.setPath(request.getContextPath());
            response.addCookie(cookie);
        }else {
            Cookie cookie = new Cookie("msg", "restoreSucceed");
            cookie.setMaxAge(3);
            cookie.setPath(request.getContextPath());
            response.addCookie(cookie);
        }
        ModelAndView modelAndView = new ModelAndView("redirect:backup");
        try {
            Thread.sleep(1000);//延时一秒钟
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return localeService.addLang(modelAndView, language);
    }

    @RequestMapping("/backup_delete")
    public ModelAndView delete(@RequestParam(value = "lang", required = false) String language,
                               @RequestParam(value = "fileName") String fileName,
                               HttpServletRequest request,
                               HttpServletResponse response) {
        backupService.delete(fileName);
        ModelAndView modelAndView = new ModelAndView("redirect:backup");
        return localeService.addLang(modelAndView, language);
    }
}
