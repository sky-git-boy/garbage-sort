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
 * 语音识别记录
 */
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "com-sky-domain-SpeechClassify")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "speech_classify")
public class SpeechClassify extends BaseEntity {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "")
    private Integer id;

    /**
     * 文件存储路径
     */
    @TableField(value = "filepath")
    @ApiModelProperty(value = "文件存储路径")
    private String filepath;

    /**
     * 一个关键字
     */
    @TableField(value = "one_keyword")
    @ApiModelProperty(value = "一个关键字")
    private String oneKeyword;

    /**
     * 一个结果
     */
    @TableField(value = "one_result")
    @ApiModelProperty(value = "一个结果")
    private String oneResult;

    /**
     * 总关键字
     */
    @TableField(value = "all_keyword")
    @ApiModelProperty(value = "总关键字")
    private String allKeyword;

    /**
     * 总结果
     */
    @TableField(value = "all_result")
    @ApiModelProperty(value = "总结果")
    private String allResult;

    /**
     * 用户名
     */
    @TableField(value = "user_name")
    @ApiModelProperty(value = "用户名")
    private String userName;

    /**
     * 时间
     */
    @TableField(value = "times")
    @ApiModelProperty(value = "时间")
    private Date times;
}