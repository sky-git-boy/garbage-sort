package com.sky.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author sky
 * @create 2021-09-18 22:58
 */
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "com-sky-dto-SpeechClassifyDto")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpeechClassifyDto extends BaseDto {
    /**
     * 一个关键字
     */
    @ApiModelProperty(value = "一个关键字")
    private String oneKeyword;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String userName;
}
