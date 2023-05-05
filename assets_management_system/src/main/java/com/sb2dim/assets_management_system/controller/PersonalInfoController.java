package com.sb2dim.assets_management_system.controller;

import com.sb2dim.assets_management_system.entity.PersonalInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 个人信息 前端控制器
 * </p>
 *
 * @author perfect imitator
 * @since 2023-03-24
 */
@RestController
@RequestMapping("/personal-info")
public class PersonalInfoController extends BaseController<PersonalInfo> {

}
