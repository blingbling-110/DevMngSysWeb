package com.qzj.devmngsys.bean;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getSession().getAttribute("username") == null) {
            request.setAttribute("permission", "请先登录系统");
            request.getRequestDispatcher("/").forward(request, response);
            return false;
        } else
            return true;
    }
}
