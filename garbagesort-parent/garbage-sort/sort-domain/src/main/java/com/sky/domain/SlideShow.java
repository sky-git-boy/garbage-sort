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
 * 幻灯片播放表
 */
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "com-sky-domain-SlideShow")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "slide_show")
public class SlideShow extends BaseEntity {
    /**
     * id
     */
    @TableId(value = "slide", type = IdType.AUTO)
    @ApiModelProperty(value = "id")
    private Integer slide;

    /**
     * 是否启用
     */
    @TableField(value = "user_id")
    @ApiModelProperty(value = "是否启用")
    private Integer userId;

    /**
     * 排序id
     */
    @TableField(value = "sort_id")
    @ApiModelProperty(value = "排序id")
    private Integer sortId;

    /**
     * 图片地址
     */
    @TableField(value = "image_url")
    @ApiModelProperty(value = "图片地址")
    private String imageUrl;

    /**
     * 跳转的链接地址
     */
    @TableField(value = "skip_url")
    @ApiModelProperty(value = "跳转的链接地址")
    private String skipUrl;
}