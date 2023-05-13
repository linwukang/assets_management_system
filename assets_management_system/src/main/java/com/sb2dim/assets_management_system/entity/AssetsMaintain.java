package com.sb2dim.assets_management_system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.sb2dim.assets_management_system.entity.auto_fill.annotation.TimestampCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Data;

/**
 * <p>
 * 资产维护
 * </p>
 *
 * @author perfect imitator
 * @since 2023-04-12
 */
@Data
@TableName("assets_maintain")
@ApiModel(value = "AssetsMaintain对象", description = "资产维护")
public class AssetsMaintain implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("维修单号")
    @TableField("code")
    @TimestampCode(prefix = "WX")
    private String code;

    @ApiModelProperty("资产id")
    @TableField("asset_id")
    // 不能为空（必填）
    @NotNull(message = "资产名称必填，请重新输入。")
    @Pattern(regexp = "^[0-9a-zA-Z\u4e00-\u9fa5]{2,30}$",
            message = "资产名称输入有误，请重新输入。")
    private Integer assetId;

    @ApiModelProperty("报修日期")
    @TableField("report_maintain_date")
    @Past(message = "报修日期输入有误，请重新输入。")
    // 不能为空（必填）
    @NotNull(message = "报修日期必填，请重新输入。")
    private Date reportMaintainDate;

    @ApiModelProperty("报修人id")
    @TableField("report_maintain_personnel_id")
    // 不能为空（必填）
    @NotNull(message = "报修人必填，请重新输入。")
    private Integer reportMaintainPersonnelId;

    @ApiModelProperty("报修原因")
    @TableField("report_maintain_reason")
    // 不能为空（必填）
    @NotBlank(message = "报修原因必填，请重新输入。")
    // 允许汉字、英文字母、数字，可输入长度小于等于20个字
    @Pattern(regexp = "^[0-9a-zA-Z\u4e00-\u9fa5]{0,20}$",
            message = "报修原因输入有误，请重新输入。")
    private String reportMaintainReason;

    @ApiModelProperty("维修单位")
    @TableField("maintain_unit")
    // 不能为空（必填）
    @NotBlank(message = "维修单位必填，请重新输入。")
    // 允许汉字、英文字母、数字，长度不超过20个字；
    @Pattern(regexp = "^[0-9a-zA-Z\u4e00-\u9fa5]{0,20}$",
            message = "维修单位输入有误，请重新输入。")
    private String maintainUnit;

    @ApiModelProperty("修复日期")
    @TableField("repair_date")
    @Past(message = "报修日期输入有误，请重新输入。")
    private Date repairDate;

    @ApiModelProperty("维修费用")
    @TableField("maintain_charge")
    private Long maintainCharge;

    @ApiModelProperty("故障说明")
    @TableField("fault_description")
    // 不能为空（必填）
    @NotBlank(message = "故障说明必填，请重新输入。")
    @Size(min = 0, max = 200,
            message = "XXXX输入有误，请重新输入。")
    private String faultDescription;
}
