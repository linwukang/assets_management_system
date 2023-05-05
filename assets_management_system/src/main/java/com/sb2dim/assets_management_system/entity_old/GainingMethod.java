package com.sb2dim.assets_management_system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.sb2dim.assets_management_system.service.GainingMethodService;
import com.sb2dim.assets_management_system.service.PersonnelService;
import com.sb2dim.assets_management_system.validation.constraints.Unique;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 取得方式
 * </p>
 *
 * @author perfect imitator
 * @since 2023-03-24
 */
@Data
@AllArgsConstructor
@TableName("gaining_method")
@ApiModel(value = "GainingMethod对象", description = "取得方式")
public class GainingMethod implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("获取方式编号")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @NotBlank(message = "获取方式编号必填，请重新输入。")
    @Unique(service = GainingMethodService.class,
            fieldName = "id",
            message = "获取方式编号不唯一，请重新输入。")
    // 可输入字母、数字，长度大于等于6个字小于等于8个字，不能以0开头
    @Pattern(regexp = "^[1-9a-zA-Z][0-9a-zA-Z]{5,7}$",
             message = "获取方式编号输入有误，请重新输入。")
    private String id;

    @ApiModelProperty("获取方式名称")
    @TableField("name")
    @NotBlank(message = "获取方式名称必填，请重新输入。")
    // 汉字，可输入长度大于等于3个字小于等于20个字
    @Pattern(regexp = "^[\u4e00-\u9fa5]{3,20}$",
             message = "获取方式名称输入有误，请重新输入。")
    private String name;

    @ApiModelProperty("是否已启用")
    @TableField("enabled")
    private Boolean enabled;

    @ApiModelProperty("创建时间")
    @TableField("create_time")
    private Date createTime;
}
