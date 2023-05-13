package com.sb2dim.assets_management_system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
    @TableField("asset_id")
    private Integer assetId;

    @ApiModelProperty("盘点结果")
    @TableField("result")
    @NotBlank(message = "盘点结果必填，请重新输入。")
    private String result;

    @ApiModelProperty("盘点备注")
    @TableField("remark")
    @NotBlank(message = "盘点备注必填，请重新输入。")
    @Size(min = 0, max = 20,
            message = "盘点备注输入有误，请重新输入。")
    private String remark;

    @ApiModelProperty("是否已盘点")
    @TableField("is_inventoried")
    private Boolean isInventoried;
}
