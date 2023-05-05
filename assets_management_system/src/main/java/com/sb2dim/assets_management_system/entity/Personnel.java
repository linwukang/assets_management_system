package com.sb2dim.assets_management_system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 人员
 * </p>
 *
 * @author perfect imitator
 * @since 2023-04-12
 */
@Data
@TableName("personnel")
@ApiModel(value = "Personnel对象", description = "人员")
public class Personnel implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("工号")
    @TableField("code")
    private String code;

    @ApiModelProperty("人员名称")
    @TableField("name")
    private String name;

    @ApiModelProperty("部门id")
    @TableField("department_id")
    private Integer departmentId;

    @ApiModelProperty("人员类别")
    @TableField("category")
    private String category;

    @ApiModelProperty("性别")
    @TableField("gender")
    private String gender;

    @ApiModelProperty("出生日期")
    @TableField("birth_date")
    private Date birthDate;

    @ApiModelProperty("个人照片")
    @TableField("identification_photo")
    private String identificationPhoto;
}
