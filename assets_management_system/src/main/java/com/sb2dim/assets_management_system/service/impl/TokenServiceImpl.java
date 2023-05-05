package com.sb2dim.assets_management_system.service.impl;

import com.sb2dim.assets_management_system.entity.UserInfo;
import com.sb2dim.assets_management_system.service.TokenService;
import com.sb2dim.assets_management_system.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;

@Service
public class TokenServiceImpl implements TokenService {
    private final HashMap<String, Pair<String, Date>> tokenMap = new HashMap<>();

    @Autowired
    UserInfoService userInfoService;

    @Override
    public boolean AddLoginToken(String token, String account, Date timeout) {
        UserInfo userInfo = userInfoService.getById(account);
        if (userInfo != null) {
            if (new Date().before(timeout)) {
                tokenMap.put(token, Pair.of(account, timeout));
                return true;
            }
        }
        return false;
    }

    @Override
    public UserInfo CheckLoginToken(String token) {
        if (token != null) {
            Pair<String, Date> pair = tokenMap.get(token);
            if (pair != null) {
                String account = pair.getFirst();
                Date timeout = pair.getSecond();
                // 如果未超时
                if (new Date().before(timeout)) {
                    // 更新 Token 的有效时间
                    tokenMap.put(token, Pair.of(account, new Date(new Date().getTime() + 12 * 60 * 60 * 1000)));
                    return userInfoService.getById(account);
                }
                else {      // Token 超时失效
                    tokenMap.remove(token);     // 移除该 Token
                }
            }
        }
        return null;
    }
}
