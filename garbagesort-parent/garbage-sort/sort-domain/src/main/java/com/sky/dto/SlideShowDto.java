package com.sky.dto;

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
@ApiModel(value = "com-sky-dto-SlideShowDto")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SlideShowDto extends BaseDto {
    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Integer slide;

    /**
     * 是否启用
     */
    @ApiModelProperty(value = "是否启用")
    private Integer userId;

    /**
     * 排序id
     */
    @ApiModelProperty(value = "排序id")
    private Integer sortId;

    /**
     * 图片地址
     */
    @ApiModelProperty(value = "图片地址")
    private String imageUrl;

    /**
     * 跳转的链接地址
     */
    @ApiModelProperty(value = "跳转的链接地址")
    private String skipUrl;
}