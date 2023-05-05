package com.sb2dim.assets_management_system.controller;

import com.sb2dim.assets_management_system.entity.AssetsBorrow;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 资产借用 前端控制器
 * </p>
 *
 * @author perfect imitator
 * @since 2023-03-24
 */
@RestController
@RequestMapping("/assets-borrow")
public class AssetsBorrowController extends BaseController<AssetsBorrow> {
}
