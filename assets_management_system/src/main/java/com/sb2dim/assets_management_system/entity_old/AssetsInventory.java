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
 * 资产盘点
 * </p>
 *
 * @author perfect imitator
 * @since 2023-03-24
 */
@Data
@AllArgsConstructor
@TableName("assets_inventory")
@ApiModel(value = "AssetsInventory对象", description = "资产盘点")
public class AssetsInventory implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("盘点单编号")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty("盘点名称")
    @TableField("name")
    private String name;

    @ApiModelProperty("盘点单状态")
    @TableField("state")
    private String state;

    @ApiModelProperty("开始日期")
    @TableField("start_date")
    private Date startDate;

    @TableField("end_date")
    private Date endDate;

    @ApiModelProperty("创建时间")
    @TableField("create_time")
    private Date createTime;
}
