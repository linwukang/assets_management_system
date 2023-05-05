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
 * 资产报废
 * </p>
 *
 * @author perfect imitator
 * @since 2023-03-24
 */
@Data
@AllArgsConstructor
@TableName("assets_scrap")
@ApiModel(value = "AssetsScrap对象", description = "资产报废")
public class AssetsScrap implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("资产编号")
    @TableId(value = "asset_id", type = IdType.ASSIGN_ID)
    private String assetId;

    @ApiModelProperty("申请人编号")
    @TableField("proposer_id")
    private String proposerId;

    @ApiModelProperty("报废方式编号")
    @TableField("scrap_mode_id")
    private String scrapModeId;

    @ApiModelProperty("报废日期")
    @TableField("scrap_date")
    private Date scrapDate;

    @ApiModelProperty("报废原因")
    @TableField("scrap_reason")
    private String scrapReason;
}
