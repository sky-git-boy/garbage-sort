package com.sky.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

import lombok.*;

/**
 * @author sky
 * @create 2021-09-08 8:25
 * 挑战结果+详情记录表
 */
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "com-sky-domain-ChallengeResult")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "challenge_result")
public class ChallengeResult extends BaseEntity {
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

    /**
     * 答题人
     */
    @TableField(value = "user_name")
    @ApiModelProperty(value = "")
    private String userName;
}