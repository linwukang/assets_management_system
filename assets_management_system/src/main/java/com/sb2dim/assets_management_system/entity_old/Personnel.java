package com.sb2dim.assets_management_system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.sb2dim.assets_management_system.service.PersonnelService;
import com.sb2dim.assets_management_system.validation.constraints.Unique;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 人员
 * </p>
 *
 * @author perfect imitator
 * @since 2023-03-24
 */
@Data
@AllArgsConstructor
@TableName("personnel")
@ApiModel(value = "Personnel对象", description = "人员")
public class Personnel implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("工号")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @NotBlank(message = "工号必填，请重新输入。")
    @Unique(service = PersonnelService.class,
            fieldName = "id",
            message = "工号不唯一，请重新输入。")
    // 必须为4位数字，不能以0开头
    @Pattern(regexp = "^[1-9]\\d{3}]$",
             message = "工号输入有误，请重新输入。")
    private String id;

    @ApiModelProperty("人员名称")
    @TableField("name")
    @NotBlank(message = "姓名必填，请重新输入。")
    // 允许汉字、英文字母、数字，可输入长度大于等于2个字小于等于10个字
    @Pattern(regexp = "^[0-9a-zA-Z\u4e00-\u9fa5]{2,10}$",
             message = "姓名输入有误，请重新输入。")
    private String name;

    @ApiModelProperty("部门编号")
    @TableField("department_id")
    @NotBlank(message = "部门编号必填，请重新输入。")
    private String departmentId;

    @ApiModelProperty("人员类别")
    @TableField("category")
    @NotBlank(message = "人员类别必填，请重新输入。")
    private String category;

    @ApiModelProperty("性别")
    @TableField("gender")
    @NotBlank(message = "性别必填，请重新输入。")
    private String gender;

    @ApiModelProperty("出生日期")
    @TableField("birth_date")
    @Past(message = "出生日期输入有误，请重新输入。")
    private Date birthDate;

    @ApiModelProperty("个人照片")
    @TableField("identification_photo")
    @Pattern(regexp = "(.jpg)|(.png)$",
             message = "请上传jpg,png类型格式文件")
    private String identificationPhoto;
}
