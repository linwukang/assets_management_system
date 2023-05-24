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
import lombok.Data;

/**
 * <p>
 * 取得方式
 * </p>
 *
 * @author perfect imitator
 * @since 2023-04-12
 */
@Data
@TableName("gaining_method")
@ApiModel(value = "GainingMethod对象", description = "取得方式")
public class GainingMethod implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("获取方式编号")
    @TableField("code")
    private String code;

    @ApiModelProperty("获取方式名称")
    @TableField("name")
    private String name;

    @ApiModelProperty("是否已启用")
    @TableField("enabled")
    private Boolean enabled;

    @ApiModelProperty("创建时间")
    @TableField("create_time")
    @Now
    private Date createTime;
}
