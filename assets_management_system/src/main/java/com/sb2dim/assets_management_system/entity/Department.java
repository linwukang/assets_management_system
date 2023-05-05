package com.sb2dim.assets_management_system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.sb2dim.assets_management_system.service.DepartmentService;
import com.sb2dim.assets_management_system.entity.auto_fill.annotation.Now;
import com.sb2dim.assets_management_system.validation.constraints.Unique;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Data;

/**
 * <p>
 * 部门
 * </p>
 *
 * @author perfect imitator
 * @since 2023-04-12
 */
@Data
@TableName("department")
@ApiModel(value = "Department对象", description = "部门")
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("部门编号")
    @TableField("code")
    // 不能为空（必填）
    @NotBlank(message = "部门编号必填，请重新输入。")
    @Pattern(regexp = "^[1-9a-zA-Z][0-9a-zA-Z]{9}$"
            ,message = "部门编号输入有误，请重新输入。")
    @Unique(service = DepartmentService.class,
            fieldName = "code",
            message = "部门编号不唯一，请重新输入。")
    private String code;

    @ApiModelProperty("部门名称")
    @TableField("name")
    // 不能为空（必填）
    @NotBlank(message = "部门名称必填，请重新输入。")
    @Pattern(regexp = "^[0-9a-zA-Z\u4e00-\u9fa5]{3,20}$"
            ,message = "部门名称输入有误，请重新输入。")
    @Unique(service = DepartmentService.class,
            fieldName = "name",
            message = "部门名称不唯一，请重新输入。")
    private String name;

    @ApiModelProperty("部门简介")
    @TableField("profile")
    @Size(max = 500)
    private String profile;

    @TableField("create_time")
    @Now
    private Date createTime;
}
