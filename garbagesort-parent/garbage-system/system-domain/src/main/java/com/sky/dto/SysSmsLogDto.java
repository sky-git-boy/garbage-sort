package com.sky.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sky.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author sky
 * @create 2021-09-20 18:48
 * 短息发送记录表
 */
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "com-sky-dto-SysSmsLogDto")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysSmsLogDto extends BaseDto {
    /**
     * 表id
     */
    @ApiModelProperty(value = "表id")
    private Long id;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String mobile;

    /**
     * 发送时间
     */
    @ApiModelProperty(value = "发送时间")
    private Date createTime;

    /**
     * 验证码
     */
    @ApiModelProperty(value = "验证码")
    private String code;

    /**
     * 0.发送成功1发送失败
     */
    @ApiModelProperty(value = "0.发送成功1发送失败")
    private String status;

    /**
     * 0注册验证码，1，挂号提醒
     */
    @ApiModelProperty(value = "0注册验证码，1，挂号提醒")
    private String type;

    /**
     * 发送失败的错误信息
     */
    @ApiModelProperty(value = "发送失败的错误信息")
    private String errorInfo;
}