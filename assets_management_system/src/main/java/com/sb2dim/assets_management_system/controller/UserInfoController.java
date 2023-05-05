package com.sb2dim.assets_management_system.controller;

import com.sb2dim.assets_management_system.entity.UserInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户登录信息 前端控制器
 * </p>
 *
 * @author perfect imitator
 * @since 2023-03-24
 */
@RestController
@RequestMapping("/user-info")
public class UserInfoController extends BaseController<UserInfo> {

}
