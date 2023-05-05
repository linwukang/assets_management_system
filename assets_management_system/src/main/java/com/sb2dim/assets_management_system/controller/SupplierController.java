package com.sb2dim.assets_management_system.controller;

import com.sb2dim.assets_management_system.entity.Supplier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 供应商 前端控制器
 * </p>
 *
 * @author perfect imitator
 * @since 2023-03-24
 */
@RestController
@RequestMapping("/supplier")
public class SupplierController extends BaseController<Supplier> {

}
