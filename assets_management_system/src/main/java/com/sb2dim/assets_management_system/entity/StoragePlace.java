package com.sb2dim.assets_management_system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.sb2dim.assets_management_system.entity.auto_fill.annotation.Now;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/**
 * <p>
 * 存放地点
 * </p>
 *
 * @author perfect imitator
 * @since 2023-04-12
 */
@Data
@TableName("storage_place")
@ApiModel(value = "StoragePlace对象", description = "存放地点")
public class StoragePlace implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("存放地点名称")
    @TableField("name")
    // 不能为空（必填）
    @NotBlank(message = "存放地点名称必填，请重新输入。")    // XXXX 填字段的中文名
    private String name;

    @ApiModelProperty("是否已启用")
    @TableField("enabled")
    private Boolean enabled;

    @ApiModelProperty("创建时间")
    @TableField("create_time")
    @Now
    private Date createTime;

    @ApiModelProperty("备注")
    @TableField("remark")
    @Pattern(regexp = "^[\u4e00-\u9fa5]{100}$",
            message = "备注输入有误，请重新输入。")
    private String remark;
}
