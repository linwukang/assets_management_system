package com.sb2dim.assets_management_system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 供应商
 * </p>
 *
 * @author perfect imitator
 * @since 2023-03-24
 */
@Data
@AllArgsConstructor
@TableName("supplier")
@ApiModel(value = "Supplier对象", description = "供应商")
public class Supplier implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("供应商编号")
    @TableId(value = "id", type = IdType.AUTO)
    @Null
    private Integer id;

    @ApiModelProperty("供应商名称")
    @TableField("name")
    @NotBlank(message = "供应商名称必填，请重新输入。")
    // 允许汉字、英文字母、数字和全角半角括号，可输入长度大于等于2个字小于等于40个字
    @Pattern(regexp = "^[\\w\u4e00-\u9fa5]{2,40}$",
             message = "供应商名称输入有误，请重新输入。")
    private String name;

    @ApiModelProperty("供应商类型")
    @TableField("type")
    @NotBlank(message = "供应商类型必填，请重新输入。")
    private String type;

    @ApiModelProperty("是否已启用")
    @TableField("enabled")
    private Boolean enabled;

    @ApiModelProperty("联系人")
    @TableField("contact_person")
    @NotBlank(message = "联系人必填，请重新输入。")
    // 允许汉字、英文字母，可输入长度大于等于2个字小于等于20个字
    @Pattern(regexp = "^[0-9a-zA-Z\u4e00-\u9fa5]{2,10}$",
             message = "联系人输入有误，请重新输入。")
    private String contactPerson;

    @ApiModelProperty("移动电话")
    @TableField("contact_tel")
    @NotBlank(message = "移动电话必填，请重新输入。")
    // 11位数字
    @Pattern(regexp = "^\\w{11}$",
             message = "移动电话输入有误，请重新输入。")
    private String contactTel;

    @ApiModelProperty("地址")
    @TableField("address")
    // 允许汉字、英文字母、数字，可输入长度大于等于2个字小于等于30个字
    @Pattern(regexp = "^[0-9a-zA-Z\u4e00-\u9fa5]{2,30}$",
             message = "地址输入有误，请重新输入。")
    private String address;

    @ApiModelProperty("备注")
    @TableField("remark")
    private String remark;

    @ApiModelProperty("创建时间")
    @TableField("create_time")
    private Date createTime;
}
