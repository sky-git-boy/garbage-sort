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
 * 搜索关键词+次数记录表
 */
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "com-sky-domain-KeywordSearchNum")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "keyword_search_num")
public class KeywordSearchNum extends BaseEntity {
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