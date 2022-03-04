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
 * 题库表
 */
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "com-sky-domain-QuestionBank")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "question_bank")
public class QuestionBank extends BaseEntity {
    /**
     * id
     */
    @TableId(value = "question_id", type = IdType.AUTO)
    @ApiModelProperty(value = "id")
    private Integer questionId;

    /**
     * 垃圾类型
     */
    @TableField(value = "garbage_type")
    @ApiModelProperty(value = "垃圾类型")
    private Byte garbageType;

    /**
     * 垃圾名称
     */
    @TableField(value = "garbage_name")
    @ApiModelProperty(value = "垃圾名称")
    private String garbageName;

    /**
     * 解析
     */
    @TableField(value = "analysis")
    @ApiModelProperty(value = "解析")
    private String analysis;

    /**
     * 备注
     */
    @TableField(value = "remark")
    @ApiModelProperty(value = "备注")
    private String remark;

    public  static String COL_QUESTION_ID = "question_id";
    public  static String COL_GARBAGE_TYPE= "garbage_type";
    public  static String COL_GARBAGE_NAME = "garbage_name";
    public  static String COL_ANALYSIS = "analysis";
    public  static String COL_REMARK = "remark";
}