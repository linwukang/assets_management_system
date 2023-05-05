package com.sb2dim.assets_management_system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.*;
import lombok.Data;

/**
 * <p>
 * 资产申购
 * </p>
 *
 * @author perfect imitator
 * @since 2023-04-12
 */
@Data
@TableName("assets_purchase_requisition")
@ApiModel(value = "AssetsPurchaseRequisition对象", description = "资产申购")
public class AssetsPurchaseRequisition implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("申购单号")
    @TableField("code")
    private String code;

    @ApiModelProperty("申购资产名")
    @TableField("asset_name")
    // 不能为空（必填）
    @NotBlank(message = "申购资产名必填，请重新输入。")    // XXXX 填字段的中文名
    // 允许汉字、英文字母、数字，可输入长度大于等于2个字小于等于30个字
    @Pattern(regexp = "^[0-9a-zA-Z\u4e00-\u9fa5]{2,30}$")
    private String assetName;

    @ApiModelProperty("资产类别id")
    @TableField("asset_class_id")
    // 不能为空（必填）
    @NotNull(message = "资产类别必填，请重新输入。")    // XXXX 填字段的中文名
    private Integer assetClassId;

    @ApiModelProperty("申请人id")
    @TableField("proposer_id")
    // 不能为空（必填）
    @NotNull(message = "申请人必填，请重新输入。")    // XXXX 填字段的中文名
    private Integer proposerId;

    @ApiModelProperty("申购数量")
    @TableField("number")
    // 不能为空（必填）
    @NotNull(message = "申购数量必填，请重新输入。")    // XXXX 填字段的中文名
    private Integer number;

    @ApiModelProperty("预计价格")
    @TableField("anticipated_price")
    private Long anticipatedPrice;

    @ApiModelProperty("设备用途id")
    @TableField("equipment_use_id")
    // 不能为空（必填）
    @NotNull(message = "设备用途必填，请重新输入。")
    private Integer equipmentUseId;

    @ApiModelProperty("资产照片")
    @TableField("asset_photograph")
    private String assetPhotograph;

    @ApiModelProperty("申购建议")
    @TableField("suggest")
    // 允许汉字、英文字母、数字，可输入长度大于等于2个字小于等于20个字
    @Pattern(regexp = "^[0-9a-zA-Z\u4e00-\u9fa5]{2,20}$"
            ,message = "申购建议输入有误，请重新输入。")
    private String suggest;

    @ApiModelProperty("申购日期")
    @TableField("purchase_requisition_date")
    @Past(message = "申购日期输入有误，请重新输入。")
    // 不能为空（必填）
    @NotNull(message = "申购日期必填，请重新输入。")
    private Date purchaseRequisitionDate;

    @ApiModelProperty("申购理由")
    @TableField("reason")
    // 可输入长度200
    @Size(max = 200)
    private String reason;

    @ApiModelProperty("申请状态")
    @TableField("application_status")
    private String applicationStatus;

    @ApiModelProperty("审核不通过原因")
    @TableField("not_approving_reasons")
    private String notApprovingReasons;
}
