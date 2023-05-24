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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * <p>
 * 报废方式
 * </p>
 *
 * @author perfect imitator
 * @since 2023-04-12
 */
@Data
@TableName("scrap_mode")
@ApiModel(value = "ScrapMode对象", description = "报废方式")
public class ScrapMode implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("报废方式编号")
    @TableField("code")
    @NotNull(message = "报废方式编号必填，请重新输入。")
    // 允许英文字母、数字，不能以0开头，小于8
    @Pattern(regexp = "^[1-9a-zA-Z][0-9a-zA-Z]{0,8}$", message = "报废方式编号输入有误，请重新输入。")
    private String code;

    @ApiModelProperty("报废方式名称")
    @TableField("name")
    @NotNull(message = "报废方式名称必填，请重新输入。")
    @Pattern(regexp = "^[0-9a-zA-Z\u4e00-\u9fa5]{2,10}$",
            message = "报废方式名称输入有误，请重新输入。")
    private String name;

    @ApiModelProperty("是否已启用")
    @TableField("enabled")
    private Boolean enabled;

    @ApiModelProperty("备注")
    @TableField("remark")
    @Size(min = 0, max = 100,
            message = "备注输入有误，请重新输入。")
    private String remark;

    @ApiModelProperty("创建时间")
    @TableField("create_time")
    @Now
    private Date createTime;
}
