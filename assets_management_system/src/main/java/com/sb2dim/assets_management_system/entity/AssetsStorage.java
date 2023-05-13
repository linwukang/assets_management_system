package com.sb2dim.assets_management_system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.Null;
import lombok.Data;

/**
 * <p>
 * 资产入库
 * </p>
 *
 * @author perfect imitator
 * @since 2023-04-12
 */
@Data
@TableName("assets_storage")
@ApiModel(value = "AssetsStorage对象", description = "资产入库")
public class AssetsStorage implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("资产编号")
    @TableField("code")
    private String code;

    @ApiModelProperty("资产名称")
    @TableField("name")
    // 允许汉字、英文字母、数字，可输入长度大于等于2个字小于等于30个字
    private String name;

    @ApiModelProperty("资产类别id")
    @TableField("asset_class_id")
    private Integer assetClassId;

    @ApiModelProperty("供应商id")
    @TableField("supplier_id")
    private Integer supplierId;

    @ApiModelProperty("品牌id")
    @TableField("brand_id")
    private Integer brandId;

    @ApiModelProperty("取得方式id")
    @TableField("gaining_method_id")
    private Integer gainingMethodId;

    @ApiModelProperty("入库日期")
    @TableField("storage_date")
    private Date storageDate;

    @ApiModelProperty("存放地点id")
    @TableField("storage_place_id")
    private Integer storagePlaceId;

    @ApiModelProperty("资产状态")
    @TableField("state")
    @Null(message = "不能指定资产状态")
    private String state;

    @ApiModelProperty("资产照片")
    @TableField("asset_photograph")
    private String assetPhotograph;
}
