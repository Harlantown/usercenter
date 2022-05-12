package com.songshu.usercenter.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.songshu.usercenter.common.constant.StringConstant.USER_LOGIN_STATE;

/**
 * @author Charmot
 * @Description
 * @create 2022-05-10 下午 9:27
 */
@Slf4j
public class InterceptorHandler implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Object loginUser = request.getSession().getAttribute(USER_LOGIN_STATE);
        if(loginUser != null){
            return true;
        }
//        request.getRequestDispatcher("/").forward(request,response);
        return false;

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
