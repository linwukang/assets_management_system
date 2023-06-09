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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @TimestampCode(prefix = "ZY")
    private String code;

    @ApiModelProperty("资产借用id")
    @TableField("assets_borrow_id")
    private Integer assetsBorrowId;

    @ApiModelProperty("新借用人id")
    @TableField("new_borrower_id")
    @NotNull(message = "新借用人必填，请重新输入。")
    private Integer newBorrowerId;

    @ApiModelProperty("转移时间")
    @TableField("transfer_date")
    @NotNull(message = "转移时间必填，请重新输入。")
    private Date transferDate;

    @ApiModelProperty("转移原因")
    @TableField("transfer_reason")
    @NotBlank(message = "转移原因必填，请重新输入。")
    @Size(min = 0, max = 200,
            message = "转移原因输入有误，请重新输入。")
    private String transferReason;
}
