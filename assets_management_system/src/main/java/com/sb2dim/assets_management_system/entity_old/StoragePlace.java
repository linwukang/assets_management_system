package com.sb2dim.assets_management_system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.sb2dim.assets_management_system.service.GainingMethodService;
import com.sb2dim.assets_management_system.service.StoragePlaceService;
import com.sb2dim.assets_management_system.validation.constraints.Unique;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 存放地点
 * </p>
 *
 * @author perfect imitator
 * @since 2023-03-24
 */
@Data
@AllArgsConstructor
@TableName("storage_place")
@ApiModel(value = "StoragePlace对象", description = "存放地点")
public class StoragePlace implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("存放地点编号")
    @TableId(value = "id", type = IdType.AUTO)
    @Null
    private Integer id;

    @ApiModelProperty("存放地点名称")
    @TableField("name")
    @NotBlank(message = "存放地点名称必填，请重新输入。")
    @Unique(service = StoragePlaceService.class,
            fieldName = "name",
            message = "存放地点名称不唯一，请重新输入。")
    // 允许汉字、英文字母、数字和全角半角括号，可输入长度大于等于2个字小于等于20个字
    @Pattern(regexp = "^[\\w\u4e00-\u9fa5]{2,20}$",
             message = "存放地点名称输入有误，请重新输入。")
    private String name;

    @ApiModelProperty("是否已启用")
    @TableField("enabled")
    private Boolean enabled;

    @ApiModelProperty("创建时间")
    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty("备注")
    @TableField("remark")
    // 字符长度不超过100字
    @Size(min = 0, max = 100, message = "备注输入有误，请重新输入。")
    private String remark;
}
