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
 * 关键词+结果记录表
 */
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "com-sky-domain-KeywordResult")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName(value = "keyword_result")
public class KeywordResult extends BaseEntity {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "")
    private Integer id;

    @TableField(value = "keyword")
    @ApiModelProperty(value = "")
    private String keyword;

    @TableField(value = "result")
    @ApiModelProperty(value = "")
    private String result;

    @TableField(value = "times")
    @ApiModelProperty(value = "")
    private Date times;
}