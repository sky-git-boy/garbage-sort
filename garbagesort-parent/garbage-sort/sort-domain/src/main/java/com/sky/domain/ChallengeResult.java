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
import lombok.NoArgsConstructor;

/**
 * @author sky
 * @create 2021-09-07 10:49
 * 挑战结果+详情记录表
 */
@ApiModel(value = "com-sky-domain-ChallengeResult")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "challenge_result")
public class ChallengeResult {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "")
    private Integer id;

    /**
     * 得分
     */
    @TableField(value = "score")
    @ApiModelProperty(value = "得分")
    private Integer score;

    /**
     * 结果集
     */
    @TableField(value = "result")
    @ApiModelProperty(value = "结果集")
    private String result;

    /**
     * 时间
     */
    @TableField(value = "times")
    @ApiModelProperty(value = "时间")
    private Date times;

    @TableField(value = "user_name")
    @ApiModelProperty(value = "")
    private String userName;
}