package com.sb2dim.assets_management_system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.sb2dim.assets_management_system.service.AssetsClassService;
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
 * 资产类别
 * </p>
 *
 * @author perfect imitator
 * @since 2023-03-24
 */
@Data
@AllArgsConstructor
@TableName("assets_class")
@ApiModel(value = "AssetsClass对象", description = "资产类别")
public class AssetsClass implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("资产类别编号")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @NotBlank(message = "资产类别必填，请重新输入。")
    @Unique(service = AssetsClassService.class,
            fieldName = "id",
            message = "类别编码不唯一，请重新输入。")
    // 可输入字母、数字，长度大于等于6个字小于等于8个字，不能以0开头
    @Pattern(regexp = "^[1-9a-zA-Z][0-9a-zA-Z]{5,7}$",
             message = "类别编码格式错误")
    private String id;

    @ApiModelProperty("资产类别名称")
    @TableField("name")
    @NotBlank(message = "资产类别名称必填，请重新输入。")
    @Unique(service = AssetsClassService.class,
            fieldName = "name",
            message = "资产类别名称不唯一，请重新输入。")
    // 汉字，可输入长度大于等于3个字小于等于20个字
    @Pattern(regexp = "^[\u4e00-\u9fa5]{3,20}$",
            message = "资产类别名称输入有误，请重新输入。")
    private String name;

    @ApiModelProperty("是否已启用")
    @TableField("enabled")
    private Boolean enabled;

    @ApiModelProperty("创建时间")
    @TableField("create_time")
    private Date createTime;
}
