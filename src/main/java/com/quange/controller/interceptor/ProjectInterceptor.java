package com.quange.controller.interceptor;

import cn.hutool.json.JSONObject;
import cn.hutool.jwt.JWT;
import com.quange.controller.Code;
import com.quange.utils.jwt.Jwt;
import lombok.val;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 拦截器
 */

@Component
public class ProjectInterceptor implements HandlerInterceptor {

    //preHandle: controller方法执行前触发，返回ture/false, ture表示通过
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // OPTIONS 请求统统放行 开发环境下跨域设置
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return true;
        }

        String token = null;

        // 获取请求的cookie
        Cookie[] cookies = request.getCookies();
        // 判断cookie 是否为空
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    token = cookie.getValue();
                }
            }
        }

        if (token == null) {
            val res = new JSONObject();
            res.set("code", Code.TOKEN_ERR);
            res.set("msg", "未登录");
            response.getWriter().append(res.toString());
            return false;
        } else {
            // 验证token
            if (!JWT.of(token).setKey(Jwt.KEY).validate(0)) {
                val res = new JSONObject();
                res.set("code", Code.TOKEN_ERR);
                res.set("msg", "token无效");
                response.getWriter().append(res.toString());
                return false;
            }
            return true;
        }

    }


    //postHandler: controller执行后，视图渲染前
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }


    //afterCompletion: 执行完毕之后触发
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
