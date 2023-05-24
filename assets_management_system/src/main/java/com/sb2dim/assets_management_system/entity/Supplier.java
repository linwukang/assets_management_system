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
import lombok.Data;

/**
 * <p>
 * 供应商
 * </p>
 *
 * @author perfect imitator
 * @since 2023-04-12
 */
@Data
@TableName("supplier")
@ApiModel(value = "Supplier对象", description = "供应商")
public class Supplier implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("名称")
    @TableField("name")
    @NotBlank(message = "名称必填，请重新输入。")
    private String name;

    @TableField("type")
    private String type;

    @ApiModelProperty("是否已启用")
    @TableField("enabled")
    private Boolean enabled;

    @ApiModelProperty("联系人")
    @TableField("contact_person")
    private String contactPerson;

    @ApiModelProperty("移动电话")
    @TableField("contact_tel")
    private String contactTel;

    @TableField("address")
    private String address;

    @TableField("remark")
    private String remark;

    @ApiModelProperty("创建时间")
    @TableField("create_time")
    @Now
    private Date createTime;
}
