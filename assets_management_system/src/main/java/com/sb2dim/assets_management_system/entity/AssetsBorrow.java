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
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * <p>
 * 资产借用
 * </p>
 *
 * @author perfect imitator
 * @since 2023-04-12
 */
@Data
@TableName("assets_borrow")
@ApiModel(value = "AssetsBorrow对象", description = "资产借用")
public class AssetsBorrow implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("")
    @TableField(value = "code")
    @TimestampCode(prefix = "JY")
    private String code;

    @ApiModelProperty("资产id")
    @TableField("asset_id")
    private Integer assetId;

    @ApiModelProperty("借用人员id")
    @TableField("borrower_id")
    // 不能为空（必填）
    @NotNull(message = "借用人员必填，请重新输入。")
    private Integer borrowerId;

    @ApiModelProperty("借用日期")
    @TableField("borrow_date")
    @Past(message = "借用日期输入有误，请重新输入。")
    // 不能为空（必填）
    @NotNull(message = "借用日期必填，请重新输入。")
    private Date borrowDate;

    @ApiModelProperty("借用原因")
    @TableField("borrow_reason")
    // 不能为空（必填）
    @NotBlank(message = "借用原因必填，请重新输入。")
    @Size(min = 0, max = 200,
            message = "借用原因输入有误，请重新输入。")
    private String borrowReason;

    @ApiModelProperty("归还日期")
    @TableField("return_date")
    @Past(message = "归还日期输入有误，请重新输入。")
//    @NotNull(message = "归还日期必填，请重新输入。")
    private Date returnDate;

    @ApiModelProperty("归还状态")
    @TableField("return_state")
    private String returnState;

    @ApiModelProperty("备注")
    @TableField("remark")
    @Size(min = 0, max = 200,
            message = "备注输入有误，请重新输入。")
    private String remark;
}
