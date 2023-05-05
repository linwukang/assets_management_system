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
 * 资产借用
 * </p>
 *
 * @author perfect imitator
 * @since 2023-03-24
 */
@Data
@AllArgsConstructor
@TableName("assets_borrow")
@ApiModel(value = "AssetsBorrow对象", description = "资产借用")
public class AssetsBorrow implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("资产编号")
    @TableField("asset_id")
    private String assetId;

    @ApiModelProperty("借用人员编号")
    @TableField("borrower_id")
    private String borrowerId;

    @ApiModelProperty("借用日期")
    @TableField("borrow_date")
    private Date borrowDate;

    @ApiModelProperty("借用原因")
    @TableField("borrow_reason")
    private String borrowReason;

    @ApiModelProperty("归还日期")
    @TableField("return_date")
    private Date returnDate;

    @ApiModelProperty("归还状态")
    @TableField("return_state")
    private String returnState;
}
