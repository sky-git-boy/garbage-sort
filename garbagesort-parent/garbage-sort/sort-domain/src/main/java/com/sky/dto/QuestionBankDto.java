package com.sky.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@ApiModel(value = "com-sky-dto-QuestionBankDto")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class QuestionBankDto extends BaseDto {
    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Integer questionId;

    /**
     * 垃圾类型
     */
    @ApiModelProperty(value = "垃圾类型")
    private String garbageType;

    /**
     * 垃圾名称
     */
    @ApiModelProperty(value = "垃圾名称")
    private String garbageName;

    /**
     * 解析
     */
    @ApiModelProperty(value = "解析")
    private String analysis;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
}
