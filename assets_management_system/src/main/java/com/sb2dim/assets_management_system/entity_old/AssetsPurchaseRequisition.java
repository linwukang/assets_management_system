package com.sb2dim.assets_management_system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 资产申购
 * </p>
 *
 * @author perfect imitator
 * @since 2023-03-24
 */
@Data
@AllArgsConstructor
@TableName("assets_purchase_requisition")
@ApiModel(value = "AssetsPurchaseRequisition对象", description = "资产申购")
public class AssetsPurchaseRequisition implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("申购单号")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty("申购资产名")
    @TableField("asset_name")
    private String assetName;

    @ApiModelProperty("资产类别编号")
    @TableField("asset_class_id")
    private String assetClassId;

    @ApiModelProperty("申请人编号")
    @TableField("proposer_id")
    private String proposerId;

    @ApiModelProperty("申购数量")
    @TableField("number")
    private Integer number;

    @ApiModelProperty("预计价格")
    @TableField("anticipated_price")
    private Long anticipatedPrice;

    @ApiModelProperty("设备用途编号")
    @TableField("equipment_use_id")
    private String equipmentUseId;

    @ApiModelProperty("资产照片")
    @TableField("asset_photograph")
    private String assetPhotograph;

    @ApiModelProperty("申购建议")
    @TableField("suggest")
    private String suggest;

    @ApiModelProperty("申购日期")
    @TableField("purchase_date")
    private Date purchaseDate;

    @ApiModelProperty("申购理由")
    @TableField("reason")
    private String reason;

    @ApiModelProperty("申请状态")
    @TableField("state")
    private String state;

    @ApiModelProperty("审核不通过原因")
    @TableField("not_approving_reasons")
    private String notApprovingReasons;
}
