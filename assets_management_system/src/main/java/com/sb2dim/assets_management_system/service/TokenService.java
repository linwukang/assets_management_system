package com.sb2dim.assets_management_system.service;

import com.sb2dim.assets_management_system.entity.UserInfo;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * Token 服务类
 * </p>
 *
 * @author perfect imitator
 * @since 2023-03-24
 */
public interface TokenService {
    boolean AddLoginToken(String token, String account, Date timeout);
    UserInfo CheckLoginToken(String token);
}
