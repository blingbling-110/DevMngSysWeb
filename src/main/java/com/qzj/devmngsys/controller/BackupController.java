package com.qzj.devmngsys.controller;

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
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class BackupController {
    @Autowired
    private LocaleService localeService;

    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String pwd;
    @Value("${spring.datasource.url}")
    private String url;

    @RequestMapping("/backup")
    public ModelAndView backup(@RequestParam(value = "lang", required = false) String language) {
        ModelAndView modelAndView = new ModelAndView("backup");
        return localeService.addLang(modelAndView, language);
    }

    @RequestMapping("/do_backup")
    public ModelAndView doBackup(@RequestParam(value = "lang", required = false) String language,
                                 HttpServletRequest request,
                                 HttpServletResponse response){
        url = url.replace("//", "");
        String database = url.substring(url.indexOf("/") + 1, url.indexOf("?"));
        Date date = new Date();//获取当前毫秒值
        /*
        设置当前时间的输出格式

        SimpleDateFormat：
        	是一个以国别敏感的方式格式化和分析数据的具体类。
        	它允许格式化 (date -> text)、语法分析 (text -> date)和标准化。
         */
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        File dir = new File("backup");
        if (!dir.exists())
            dir.mkdirs();
        String backupPath = "backup/" + database + "_" + sdf.format(date) + ".sql";
        String cmd = String.format("mysqldump -u%s -p%s %s > %s", username, pwd, database, backupPath);
        try {
            if (System.getProperty("os.name").contains("Windows"))
                Runtime.getRuntime().exec(new String[]{"cmd", "/C", cmd});
            else
                Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", cmd});
            Cookie cookie = new Cookie("msg", backupPath);
            cookie.setMaxAge(5);
            cookie.setPath(request.getContextPath());
            response.addCookie(cookie);
        } catch (IOException e) {
            e.printStackTrace();
            Cookie cookie = new Cookie("msg", "error");
            cookie.setMaxAge(5);
            cookie.setPath(request.getContextPath());
            response.addCookie(cookie);
        }
        ModelAndView modelAndView = new ModelAndView("redirect:backup");
        return localeService.addLang(modelAndView, language);
    }
}
