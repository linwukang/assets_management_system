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
 * 个人信息
 * </p>
 *
 * @author perfect imitator
 * @since 2023-04-12
 */
@Data
@TableName("personal_info")
@ApiModel(value = "PersonalInfo对象", description = "个人信息")
public class PersonalInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("账号")
    @TableId(value = "account", type = IdType.ASSIGN_ID)
    private String account;

    @ApiModelProperty("手机号")
    @TableField("tel")
    private String tel;

    @ApiModelProperty("最后登录时间")
    @TableField("login_last_time")
    private Date loginLastTime;

    @ApiModelProperty("登录次数")
    @TableField("login_times")
    private Integer loginTimes;
}
