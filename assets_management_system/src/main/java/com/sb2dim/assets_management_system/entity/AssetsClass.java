package com.sb2dim.assets_management_system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.sb2dim.assets_management_system.entity.auto_fill.annotation.Now;
import com.sb2dim.assets_management_system.service.AssetsClassService;
import com.sb2dim.assets_management_system.validation.constraints.Unique;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/**
 * <p>
 * 资产类别
 * </p>
 *
 * @author perfect imitator
 * @since 2023-04-12
 */
@Data
@TableName("assets_class")
@ApiModel(value = "AssetsClass对象", description = "资产类别")
public class AssetsClass implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("资产类别编号")
    @TableField("code")
    // 不能为空（必填）
    @NotBlank(message = "资产类别编号必填，请重新输入。")
    @Unique(service = AssetsClassService.class,
            fieldName = "code",
            message = "资产类别编号不唯一，请重新输入。")
    // 允许英文字母、数字，不能以0开头，大于6小于8
    @Pattern(regexp = "^[1-9a-zA-Z][0-9a-zA-Z]{5,7}$",
            message = "资产类别编号输入有误，请重新输入。")
    private String code;

    @ApiModelProperty("资产类别名称")
    @TableField("name")
    @NotBlank(message = "资产类别名称必填，请重新输入。")
    // 汉字，可输入长度大于等于3个字小于等于20个字
    @Pattern(regexp = "^[\u4e00-\u9fa5]{3,20}$",
            message = "资产类别名称输入有误，请重新输入。")
    // 汉字，可输入长度大于等于3个字小于等于20个字
    private String name;

    @ApiModelProperty("是否已启用")
    @TableField("enabled")
    private Boolean enabled;

    @ApiModelProperty("创建时间")
    @TableField("create_time")
    @Now
    private Date createTime;
}
