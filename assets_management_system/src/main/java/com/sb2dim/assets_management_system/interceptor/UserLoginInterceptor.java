package com.sb2dim.assets_management_system.interceptor;

import com.sb2dim.assets_management_system.controller.result.Code;
import com.sb2dim.assets_management_system.entity.UserInfo;
import com.sb2dim.assets_management_system.service.TokenService;
import com.sb2dim.assets_management_system.service.UserInfoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.PrintWriter;


@Component
public class UserLoginInterceptor implements HandlerInterceptor {
    private final Logger logger = LoggerFactory.getLogger(UserLoginInterceptor.class);

    @Autowired
    private TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (true) {
            return true;
        }

        String url = request.getRequestURI();
        String token = request.getHeader("token");
        String method = request.getMethod();
        if (!method.equals("OPTIONS")){
            logger.info(token);
            logger.info(url);
            logger.info(method);
            // 查询验证token
            UserInfo userByToken = tokenService.CheckLoginToken(token);
            if (userByToken == null) {
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json; charset=utf-8");
                PrintWriter out = response.getWriter();
                try{
                    System.out.println("用户未登录");
                    JSONObject json = new JSONObject();
                    json.put("message", "用户未登录");
                    json.put("code", Code.NOT_LOGGED_IN);
                    response.setContentType("application/json");
                    // 返回json信息给前端
                    out.append(json.toString());
                    out.flush();
                    return false;
                } catch (Exception e){
                    e.printStackTrace();
                    response.sendError(500);
                    return false;
                }
            }
            System.out.println("user: " + userByToken);
            return true;
        }
        return false;
    }
}
