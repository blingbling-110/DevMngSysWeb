package com.qzj.devmngsys.controller;

import com.qzj.devmngsys.service.LocaleService;
import com.qzj.devmngsys.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Controller
public class LoginController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private LocaleService localeService;

    @PostMapping("/login")
    public ModelAndView login(@RequestParam("username") String username,
                              @RequestParam("password") String password,
                              @RequestParam(value = "lang", required = false) String language,
                              @RequestParam(value = "remember", required = false) String remember,
                              HttpSession httpSession,
                              RedirectAttributes redirectAttributes,
                              HttpServletRequest request,
                              HttpServletResponse response) {
        ModelAndView modelAndView;
        if (loginService.login(username, password)) {//验证用户名密码是否正确
            httpSession.setAttribute("tbUserInfo", loginService.tbUserInfo);
            modelAndView = new ModelAndView("redirect:to_dashboard");//重定向，防止表单重复提交
            if ("on".equals(remember)) {//是否记住用户登录状态，利用cookie实现
                Cookie cookie = new Cookie("username", username);
                cookie.setMaxAge(90 * 24 * 60 * 60);//cookie过期时间设置为三个月
                cookie.setPath(request.getContextPath());
                response.addCookie(cookie);
                cookie = new Cookie("password", URLEncoder.encode(password, StandardCharsets.UTF_8));
                cookie.setMaxAge(90 * 24 * 60 * 60);//cookie过期时间设置为三个月
                cookie.setPath(request.getContextPath());
                response.addCookie(cookie);
            }
        } else {
            redirectAttributes.addFlashAttribute("error", "用户名或密码错误");
            modelAndView = new ModelAndView("redirect:/index");
        }
        return localeService.addLang(modelAndView, language);
    }

    @RequestMapping("/sign_out")
    public ModelAndView signOut(@RequestParam(value = "lang", required = false) String language,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        HttpSession httpSession = request.getSession(false);//防止创建会话
        httpSession.removeAttribute("username");
        httpSession.removeAttribute("isAdmin");
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (StringUtils.equals(cookie.getName(), "username")) {
                    cookie.setMaxAge(0);//使cookie过期
                    cookie.setPath(request.getContextPath());
                    response.addCookie(cookie);
                } else if (StringUtils.equals(cookie.getName(), "password")) {
                    cookie.setMaxAge(0);
                    cookie.setPath(request.getContextPath());
                    response.addCookie(cookie);
                }
            }
        }
        return localeService.addLang(new ModelAndView("redirect:/index"), language);
    }

    @RequestMapping("/password")
    public ModelAndView password(@RequestParam(value = "lang", required = false) String language,
                                 @RequestParam("pwd_ori") String pwd_ori,
                                 @RequestParam("pwd_new") String pwd_new,
                                 @RequestParam("pwd_cfm") String pwd_cfm,
                                 @RequestParam("requestURL") String requestURL,
                                 @RequestParam("username") String username,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
        int result = loginService.changePwd(pwd_ori, pwd_new, pwd_cfm, username);
        if (result == 0) {
            Cookie cookie = new Cookie("msg", "PwdChanged");
            cookie.setMaxAge(3);
            cookie.setPath(request.getContextPath());
            response.addCookie(cookie);
        }else if (result == -1) {
            Cookie cookie = new Cookie("msg", "ConfirmPwd");
            cookie.setMaxAge(3);
            cookie.setPath(request.getContextPath());
            response.addCookie(cookie);
        }else if (result == -2) {
            Cookie cookie = new Cookie("msg", "OriPwd");
            cookie.setMaxAge(3);
            cookie.setPath(request.getContextPath());
            response.addCookie(cookie);
        }
        ModelAndView modelAndView = new ModelAndView("redirect:"
                + requestURL.substring(requestURL.lastIndexOf("/")));
        return localeService.addLang(modelAndView, language);
    }
}
