package com.sb2dim.assets_management_system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.sb2dim.assets_management_system.service.EquipmentUseService;
import com.sb2dim.assets_management_system.service.ScrapModeService;
import com.sb2dim.assets_management_system.validation.constraints.Unique;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 设备用途
 * </p>
 *
 * @author perfect imitator
 * @since 2023-03-24
 */
@Data
@AllArgsConstructor
@TableName("equipment_use")
@ApiModel(value = "EquipmentUse对象", description = "设备用途")
public class EquipmentUse implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("设备用途编号")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @NotBlank(message = "设备用途编号必填，请重新输入。")
    @Unique(service = EquipmentUseService.class,
            fieldName = "id",
            message = "设备用途编号不唯一，请重新输入。")
    // 允许英文字母、数字，不能以0开头，长度必须为8个字
    @Pattern(regexp = "^[1-9a-zA-Z][0-9a-zA-Z]{7}$",
            message = "设备用途编号格式错误")
    private String id;

    @ApiModelProperty("设备用途名称")
    @TableField("name")
    @NotBlank(message = "设备用途名称必填，请重新输入。")
    // 允许汉字、英文字母、数字，可输入长度大于等于2个字小于等于10个字
    @Pattern(regexp = "^[0-9a-zA-Z\u4e00-\u9fa5]{2,10}$",
            message = "设备用途名称输入有误，请重新输入。")
    private String name;

    @ApiModelProperty("是否已启用")
    @TableField("enabled")
    private Boolean enabled;

    @ApiModelProperty("备注")
    @TableField("remark")
    // 字符长度不超过100个字
    @Size(min = 0, max = 100, message = "备注输入有误，请重新输入。")
    private String remark;

    @ApiModelProperty("创建时间")
    @TableField("create_time")
    private Date createTime;
}
