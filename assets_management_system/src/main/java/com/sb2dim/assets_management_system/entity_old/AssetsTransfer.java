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
 * 资产转移
 * </p>
 *
 * @author perfect imitator
 * @since 2023-03-24
 */
@Data
@AllArgsConstructor
@TableName("assets_transfer")
@ApiModel(value = "AssetsTransfer对象", description = "资产转移")
public class AssetsTransfer implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("assets_borrow_id")
    private Integer assetsBorrowId;

    @ApiModelProperty("新借用人编号")
    @TableField("new_borrower_id")
    private String newBorrowerId;

    @ApiModelProperty("转移时间")
    @TableField("transfer_time")
    private Date transferTime;

    @ApiModelProperty("转移原因")
    @TableField("transfer_reason")
    private String transferReason;
}
