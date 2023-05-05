package com.sb2dim.assets_management_system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 资产盘点单
 * </p>
 *
 * @author perfect imitator
 * @since 2023-04-12
 */
@Data
@TableName("assets_inventory_sheet")
@ApiModel(value = "AssetsInventorySheet对象", description = "资产盘点单")
public class AssetsInventorySheet implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("盘点单id")
    @TableField("assets_inventory_id")
    private Integer assetsInventoryId;

    @ApiModelProperty("资产id")
    @TableField("assets_id")
    private Integer assetsId;

    @ApiModelProperty("盘点结果")
    @TableField("result")
    private String result;

    @ApiModelProperty("盘点备注")
    @TableField("remark")
    private String remark;
}
