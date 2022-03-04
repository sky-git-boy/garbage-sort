package com.sky.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author sky
 * @create 2021-09-07 10:49
 * 挑战明细记录
 */
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "com-sky-domain-ChallengeDetail")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "challenge_detail")
public class ChallengeDetail extends BaseEntity {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "")
    private Integer id;

    /**
     * 是否正确；1:正确；0:错误
     */
    @TableField(value = "whether")
    @ApiModelProperty(value = "是否正确；1:正确；0:错误")
    private Byte whether;

    /**
     * 问题id

     */
    @TableField(value = "question_id")
    @ApiModelProperty(value = "问题id,")
    private Integer questionId;

    @TableField(value = "garbage_name")
    @ApiModelProperty(value = "")
    private String garbageName;

    @TableField(value = "garbage_type")
    @ApiModelProperty(value = "")
    private Byte garbageType;

    @TableField(value = "selected_type")
    @ApiModelProperty(value = "")
    private Byte selectedType;
}