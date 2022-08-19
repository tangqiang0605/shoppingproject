package com.example.demo.interceptor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class MyInterceptor implements HandlerInterceptor {

    @Value("${manager.switch}")
    private Boolean managerPattern;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("后端拦截器启动");
        String requestURI = request.getRequestURI();
        if(requestURI.startsWith("/manager")){
//            return managerPattern;
        }
        return true;
    }
}