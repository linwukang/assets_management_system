package com.sb2dim.assets_management_system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.sb2dim.assets_management_system.entity.auto_fill.annotation.Now;
import com.sb2dim.assets_management_system.entity.auto_fill.annotation.TimestampCode;
import com.sb2dim.assets_management_system.service.AssetsInventoryService;
import com.sb2dim.assets_management_system.service.DepartmentService;
import com.sb2dim.assets_management_system.validation.constraints.Unique;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
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
    @TimestampCode(prefix = "PD")
    private String code;

    @ApiModelProperty("盘点名称")
    @TableField("name")
    // 不能为空（必填）
    @NotBlank(message = "盘点名称必填，请重新输入。")    // XXXX 填字段的中文名
    @Pattern(regexp = "^[0-9a-zA-Z\u4e00-\u9fa5]{0,20}$",
            message = "盘点名称输入有误，请重新输入。")    // XXXX 填字段的中文名
    @Unique(service = AssetsInventoryService.class,
            fieldName = "name",
            message = "盘点名称不唯一，请重新输入。")
    private String name;

    @ApiModelProperty("盘点说明")
    @TableField("description")
    // 不能为空（必填）
    @NotBlank(message = "盘点说明必填，请重新输入。")    // XXXX 填字段的中文名
    @Pattern(regexp = "^[0-9a-zA-Z\u4e00-\u9fa5]{0,20}$",
            message = "盘点说明输入有误，请重新输入。")    // XXXX 填字段的中文名
    private String description;

    @ApiModelProperty("盘点单状态")
    @TableField("state")
    private String state;

    @ApiModelProperty("开始日期")
    @TableField("start_date")
    @Past
    private Date startDate;

    @ApiModelProperty("结束日期")
    @TableField("end_date")
    private Date endDate;

    @ApiModelProperty("创建时间")
    @TableField("create_time")
    @Now
    private Date createTime;
}
