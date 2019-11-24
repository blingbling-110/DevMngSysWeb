package com.qzj.devmngsys.bean;

import org.springframework.web.servlet.HandlerInterceptor;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String username = null;
        String password = null;
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
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            if (request.getSession().getAttribute("username") == null) {
                request.setAttribute("permission", "请先登录系统");
                request.getRequestDispatcher("/").forward(request, response);//请求转发
                return false;
            } else
                return true;
        }
        request.getSession().setAttribute("username", username);
        return true;
    }
}
