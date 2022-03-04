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
 * 搜索关键词+次数记录表
 */
@ApiModel(value = "com-sky-domain-KeywordSearchNum")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "keyword_search_num")
public class KeywordSearchNum {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "")
    private Integer id;

    @TableField(value = "keyword")
    @ApiModelProperty(value = "")
    private String keyword;

    @TableField(value = "num")
    @ApiModelProperty(value = "")
    private Integer num;

    @TableField(value = "times")
    @ApiModelProperty(value = "")
    private Date times;
}