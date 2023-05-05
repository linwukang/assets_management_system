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
 * 资产维护
 * </p>
 *
 * @author perfect imitator
 * @since 2023-03-24
 */
@Data
@AllArgsConstructor
@TableName("assets_maintain")
@ApiModel(value = "AssetsMaintain对象", description = "资产维护")
public class AssetsMaintain implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("维修单号")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @TableField("asset_id")
    private String assetId;

    @ApiModelProperty("报修日期")
    @TableField("report_maintain_date")
    private Date reportMaintainDate;

    @ApiModelProperty("报修人编号")
    @TableField("report_maintain_personnel_id")
    private String reportMaintainPersonnelId;

    @ApiModelProperty("报修原因")
    @TableField("report_maintain_reason")
    private String reportMaintainReason;

    @ApiModelProperty("维修单位")
    @TableField("maintain_unit")
    private String maintainUnit;

    @ApiModelProperty("修复日期")
    @TableField("repair_date")
    private Date repairDate;

    @ApiModelProperty("维修费用")
    @TableField("maintain_charge")
    private Long maintainCharge;

    @ApiModelProperty("故障说明")
    @TableField("fault_description")
    private String faultDescription;
}
