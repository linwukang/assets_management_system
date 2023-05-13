package com.sb2dim.assets_management_system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * <p>
 * 设备用途
 * </p>
 *
 * @author perfect imitator
 * @since 2023-04-12
 */
@Data
@TableName("equipment_use")
@ApiModel(value = "EquipmentUse对象", description = "设备用途")
public class EquipmentUse implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)

    private Integer id;

    @ApiModelProperty("设备用途编号")
    @TableField("code")
    // 不能为空（必填）
    @NotNull(message = "设备用途编号必填，请重新输入。")
    // 允许英文字母、数字，不能以0开头，小于8
    @Pattern(regexp = "^[1-9a-zA-Z][0-9a-zA-Z]{0,8}$", message = "设备用途编号输入有误，请重新输入。")
    private String code;

    @ApiModelProperty("设备用途名称")
    @TableField("name")
    @NotNull(message = "设备用途名称必填，请重新输入。")
    // 允许英文字母、数字，不能以0开头，大于2小于10
    @Pattern(regexp = "^[1-9a-zA-Z][0-9a-zA-Z]{2,10}$")
    private String name;

    @ApiModelProperty("是否已启用")
    @TableField("enabled")
    private Boolean enabled;

    @ApiModelProperty("备注")
    @TableField("remark")
    // 汉字，可输入长度100
    @Size(min = 0, max = 100,
            message = "备注输入有误，请重新输入。")
    private String remark;

    @ApiModelProperty("创建时间")
    @TableField("create_time")
    private Date createTime;
}
