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
 * 资产入库
 * </p>
 *
 * @author perfect imitator
 * @since 2023-03-24
 */
@Data
@AllArgsConstructor
@TableName("assets_storage")
@ApiModel(value = "AssetsStorage对象", description = "资产入库")
public class AssetsStorage implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("资产编号")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty("资产名称")
    @TableField("asset_name")
    private String assetName;

    @ApiModelProperty("资产类别编号")
    @TableField("asset_class_id")
    private String assetClassId;

    @ApiModelProperty("供应商编号")
    @TableField("supplier_id")
    private Integer supplierId;

    @ApiModelProperty("品牌编号")
    @TableField("brand_id")
    private Integer brandId;

    @ApiModelProperty("取得方式编号")
    @TableField("gaining_method_id")
    private String gainingMethodId;

    @ApiModelProperty("入库时间")
    @TableField("storage_time")
    private Date storageTime;

    @ApiModelProperty("存放地点编号")
    @TableField("storage_place_id")
    private Integer storagePlaceId;

    @ApiModelProperty("资产照片")
    @TableField("asset_photograph")
    private String assetPhotograph;
}
