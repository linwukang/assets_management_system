package com.sb2dim.assets_management_system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sb2dim.assets_management_system.controller.result.Code;
import com.sb2dim.assets_management_system.controller.result.ResponseResult;
import com.sb2dim.assets_management_system.entity.AssetsBorrow;
import com.sb2dim.assets_management_system.entity.Personnel;
import com.sb2dim.assets_management_system.service.AssetsBorrowService;
import com.sb2dim.assets_management_system.service.PersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

/**
 * <p>
 * 人员 前端控制器
 * </p>
 *
 * @author perfect imitator
 * @since 2023-03-24
 */
@RestController
@RequestMapping("/personnel")
public class PersonnelController extends BaseController<Personnel> {

    @Autowired
    AssetsBorrowService assetsBorrowService;

    @Autowired
    PersonnelService personnelService;

    @Override
    @DeleteMapping("/{id}")
    public ResponseResult deleteById(@PathVariable Serializable id) {
        long borrowCount = assetsBorrowService.count(
                new QueryWrapper<AssetsBorrow>()
                        .eq("borrower_id", id)
                        .eq("return_state", "returned")
        );

        Personnel personnel = personnelService.getById(id);
        if (borrowCount > 0) {
            return new ResponseResult(
                    personnel,
                    Code.DELETE_ERR,
                    personnel.getName() + "（工号：" + personnel.getCode() + ")还有正在借用的资产，无法删除。");
        }
        else {
            personnel.setCategory("离任");
            boolean succeeded = personnelService.saveOrUpdate(personnel);
            return new ResponseResult(
                    succeeded,
                    succeeded ? Code.DELETE_OK : Code.DELETE_ERR,
                    succeeded ? null : "服务器繁忙，请稍后重试！");
        }
    }
}
