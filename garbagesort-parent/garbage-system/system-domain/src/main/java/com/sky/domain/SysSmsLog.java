package com.sky.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author sky
 * @create 2021-09-20 18:48
 * 短息发送记录表
 */
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "com-sky-domain-SysSmsLog")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_sms_log")
public class SysSmsLog extends BaseEntity {
    /**
     * 表id
     */
    @TableId(value = "id", type = IdType.INPUT)
    @ApiModelProperty(value = "表id")
    private Long id;

    /**
     * 手机号
     */
    @TableField(value = "mobile")
    @ApiModelProperty(value = "手机号")
    private String mobile;

    /**
     * 发送时间
     */
    @TableField(value = "create_time")
    @ApiModelProperty(value = "发送时间")
    private Date createTime;

    /**
     * 验证码
     */
    @TableField(value = "code")
    @ApiModelProperty(value = "验证码")
    private String code;

    /**
     * 0.发送成功1发送失败
     */
    @TableField(value = "status")
    @ApiModelProperty(value = "0.发送成功1发送失败")
    private String status;

    /**
     * 0注册验证码，1，挂号提醒
     */
    @TableField(value = "type")
    @ApiModelProperty(value = "0注册验证码，1，挂号提醒")
    private String type;

    /**
     * 发送失败的错误信息
     */
    @TableField(value = "error_info")
    @ApiModelProperty(value = "发送失败的错误信息")
    private String errorInfo;
}