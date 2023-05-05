package com.sb2dim.assets_management_system.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import com.sb2dim.assets_management_system.service.DepartmentService;
import com.sb2dim.assets_management_system.validation.constraints.Now;
import com.sb2dim.assets_management_system.validation.constraints.Unique;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;


/**
 * <p>
 * 部门
 * </p>
 *
 * @author perfect imitator
 * @since 2023-03-24
 */
@Data
@AllArgsConstructor
@TableName("department")
@ApiModel(value = "Department对象", description = "部门")
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("部门编码")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @NotBlank(message = "部门编码必填，请重新输入。")
    @Unique(service = DepartmentService.class,
            fieldName = "id",
            message = "部门编码不唯一，请重新输入。")
    // 允许英文字母、数字，不能以0开头，长度必须为10个字
    @Pattern(regexp = "^[1-9a-zA-Z][0-9a-zA-Z]{9}$",
            message = "部门编号格式错误")
    private String id;

    @ApiModelProperty("部门名称")
    @TableField("name")
    @NotBlank(message = "部门编码必填，请重新输入。")
    // 允许汉字、英文字母、数字，可输入长度大于等于3个字小于等于20个字
    @Pattern(regexp = "^[0-9a-zA-Z\u4e00-\u9fa5]{3,20}$",
            message = "部门名称格式错误")
    @Unique(service = DepartmentService.class,
            fieldName = "name",
            message = "部门名称不唯一，请重新输入。")
    private String name;

    @ApiModelProperty("部门简介")
    @TableField("profile")
    @Size(min = 0, max = 500, message = "部门简介输入有误，请重新输入。")
    private String profile;

    @TableField(value = "create_time")
    @Now
    private Date createTime;
}
