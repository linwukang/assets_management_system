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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/**
 * <p>
 * 资产报废
 * </p>
 *
 * @author perfect imitator
 * @since 2023-04-12
 */
@Data
@TableName("assets_scrap")
@ApiModel(value = "AssetsScrap对象", description = "资产报废")
public class AssetsScrap implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "asset_id", type = IdType.ASSIGN_ID)
    private Integer id;

    @ApiModelProperty("资产id")
    @TableField(value = "asset_id")
    private Integer assetId;

    @ApiModelProperty("报废单号")
    @TableField("code")
    @TimestampCode(prefix = "BF")
    private String code;

    @ApiModelProperty("申请人id")
    @TableField("proposer_id")
    private Integer proposerId;

    @ApiModelProperty("报废方式id")
    @TableField("scrap_mode_id")
    private Integer scrapModeId;

    @ApiModelProperty("报废日期")
    @TableField("scrap_date")
    private Date scrapDate;

    @ApiModelProperty("报废原因")
    @TableField("scrap_reason")
    private String scrapReason;

    @ApiModelProperty("申请状态")
    @TableField("application_status")
    private String applicationStatus;

    @ApiModelProperty("审核不通过原因")
    @TableField("not_approving_reasons")
    @NotBlank(message = "审核不通过原因必填，请重新输入。")
    @Pattern(regexp = "^[0-9a-zA-Z\u4e00-\u9fa5]{1,100}$",
            message = "审核不通过原因输入有误，请重新输入。")
    private String notApprovingReasons;
}
