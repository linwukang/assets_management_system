package com.sb2dim.assets_management_system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 资产转移
 * </p>
 *
 * @author perfect imitator
 * @since 2023-04-12
 */
@Data
@TableName("assets_transfer")
@ApiModel(value = "AssetsTransfer对象", description = "资产转移")
public class AssetsTransfer implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("转移单号")
    @TableField("code")
    private String code;

    @ApiModelProperty("资产借用id")
    @TableField("assets_borrow_id")
    private Integer assetsBorrowId;

    @ApiModelProperty("新借用人id")
    @TableField("new_borrower_id")
    private Integer newBorrowerId;

    @ApiModelProperty("转移时间")
    @TableField("transfer_date")
    private Date transferDate;

    @ApiModelProperty("转移原因")
    @TableField("transfer_reason")
    private String transferReason;
}
