package com.sb2dim.assets_management_system.controller;

import com.sb2dim.assets_management_system.controller.result.Code;
import com.sb2dim.assets_management_system.controller.result.ResponseResult;
import com.sb2dim.assets_management_system.entity.PersonalInfo;
import com.sb2dim.assets_management_system.entity.UserInfo;
import com.sb2dim.assets_management_system.service.PersonalInfoService;
import com.sb2dim.assets_management_system.service.TokenService;
import com.sb2dim.assets_management_system.service.UserInfoService;
import org.apache.tomcat.util.json.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/")
public class LoginController {

    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private PersonalInfoService personalInfoService;

    @GetMapping("/login")
    public ResponseResult login(@RequestParam String username, @RequestParam String password) {
        UserInfo userInfo = userInfoService.getById(username);
        String message = "服务器繁忙，请稍后重试！";
        if (userInfo == null) {
            message = "登录账号不存在！";
        }
        else if (!userInfo.getPassword().equals(password)) {
            message = "登录密码错误!";
        }
        // 登录成功
        else {
            String token = UUID.randomUUID().toString();
            tokenService.AddLoginToken(token, username, new Date(new Date().getTime() + 12 * 60 * 60 * 1000));

            PersonalInfo personalInfo = personalInfoService.getById(username);
            if (personalInfo != null) {
                // 登录次数加一
                personalInfo.setLoginTimes(personalInfo.getLoginTimes() + 1);
                // 最后一次登录时间为当前时间
                personalInfo.setLoginLastTime(new Date());
                personalInfoService.saveOrUpdate(personalInfo);
            }
            else {
                personalInfo = new PersonalInfo();
                personalInfo.setAccount(username);
                personalInfo.setLoginLastTime(new Date());
                personalInfo.setLoginTimes(1);
                personalInfoService.save(personalInfo);
            }

            return new ResponseResult(token, Code.LOGIN_SUCCEED);
        }
        // 登陆失败
        return new ResponseResult(null, Code.LOGIN_FAILED, message);
    }

    @GetMapping("/user-info/personal-info")
    public ResponseResult personalInfo(@RequestHeader("token") String token) {
        if (token != null) {
            UserInfo userInfo = tokenService.CheckLoginToken(token);
            if (userInfo != null) {
                PersonalInfo personalInfo = personalInfoService.getById(userInfo.getUsername());
                if (personalInfo != null) {
                    return new ResponseResult(
                            personalInfo,
                            Code.QUERY_OK
                    );
                }
            }
        }
        return new ResponseResult(
                null,
                Code.LOGIN_FAILED,
                "用户未登录"
        );
    }

    @GetMapping("/user-info/identity")
    public ResponseResult identity(@RequestHeader("token") String token) {
        System.out.println("identity");
        if (token != null) {
            UserInfo userInfo = tokenService.CheckLoginToken(token);
            System.out.println(userInfo);
            if (userInfo != null) {
                return new ResponseResult(
                        userInfo.getIdentity(),
                        Code.QUERY_OK
                );

            }
        }
        return new ResponseResult(
                null,
                Code.LOGIN_FAILED,
                "用户未登录"
        );
    }

}
