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
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/**
 * <p>
 * 品牌
 * </p>
 *
 * @author perfect imitator
 * @since 2023-04-12
 */
@Data
@TableName("brand")
@ApiModel(value = "Brand对象", description = "品牌")
public class Brand implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    @Null
    private Integer id;

    @ApiModelProperty("品牌名称")
    @TableField("name")
    @NotBlank(message = "品牌名称必填，请重新输入。")
    // 汉字，可输入长度大于等于3个字小于等于20个字
    @Pattern(regexp = "^[\u4e00-\u9fa5]{3,20}$",
            message = "品牌名称输入有误，请重新输入。")
    private String name;

    @ApiModelProperty("品牌说明")
    @TableField("description")
    @NotBlank(message = "品牌说明必填，请重新输入。")
    private String description;

    @ApiModelProperty("是否已启用")
    @TableField("enabled")
    private Boolean enabled;

    @ApiModelProperty("创建时间")
    @TableField("create_time")
    @Now
    private Date createTime;
}
