package com.qzj.devmngsys.bean;

import com.qzj.devmngsys.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Autowired
    private LoginService loginService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String username = null;
        String password = null;

        //登录成功则放行
        if (request.getSession().getAttribute("tbUserInfo") != null) {
            if ("/".equals(request.getRequestURI()))
                request.getRequestDispatcher("/toDashboard").forward(request, response);//请求转发
            return true;
        }

        //获取用户名、密码cookie
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0) {
            //获取登录cookie
            for (Cookie cookie : cookies) {
                if (StringUtils.equals(cookie.getName(), "username")) {
                    username = cookie.getValue();
                } else if (StringUtils.equals(cookie.getName(), "password")) {
                    password = cookie.getValue();
                }
            }
        }

        //没有用户名、密码cookie则转向登录页面
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            if (!"/".equals(request.getRequestURI()))
                request.setAttribute("permission", "请先登录系统");
            request.getRequestDispatcher("/index").forward(request, response);//请求转发
            return false;
        }
        if (loginService.login(username, password)) {//有用户名、密码cookie则验证cookie
            request.getSession().setAttribute("tbUserInfo", loginService.tbUserInfo);
            if ("/".equals(request.getRequestURI()))
                request.getRequestDispatcher("/dashboard").forward(request, response);//请求转发
            return true;
        }
        request.setAttribute("error", "用户名或密码错误");
        request.getRequestDispatcher("/index").forward(request, response);//请求转发
        return false;
    }
}
