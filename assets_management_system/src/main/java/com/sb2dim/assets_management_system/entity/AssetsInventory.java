package com.sb2dim.assets_management_system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/**
 * <p>
 * 资产盘点
 * </p>
 *
 * @author perfect imitator
 * @since 2023-04-12
 */
@Data
@TableName("assets_inventory")
@ApiModel(value = "AssetsInventory对象", description = "资产盘点")
public class AssetsInventory implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("盘点单编号")
    @TableField("code")
    private String code;

    @ApiModelProperty("盘点名称")
    @TableField("name")
    // 不能为空（必填）
    @NotBlank(message = "盘点名称必填，请重新输入。")    // XXXX 填字段的中文名
    @Pattern(regexp = "^[0-9a-zA-Z\u4e00-\u9fa5]{20}$",
            message = "盘点名称输入有误，请重新输入。")    // XXXX 填字段的中文名
    private String name;

    @ApiModelProperty("盘点单状态")
    @TableField("state")
    // 不能为空（必填）
    @NotBlank(message = "盘点单状态必填，请重新输入。")    // XXXX 填字段的中文名
    @Pattern(regexp = "^[0-9a-zA-Z\u4e00-\u9fa5]{20}$",
            message = "盘点单状态入有误，请重新输入。")    // XXXX 填字段的中文名
    private String state;

    @ApiModelProperty("开始日期")
    @TableField("start_date")
    private Date startDate;

    @ApiModelProperty("结束日期")
    @TableField("end_date")
    private Date endDate;

    @ApiModelProperty("创建时间")
    @TableField("create_time")
    private Date createTime;
}
