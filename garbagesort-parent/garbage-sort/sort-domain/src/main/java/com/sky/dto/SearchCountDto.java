package com.sky.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@ApiModel(value = "com-com.sky-dto-SearchCountDto")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class SearchCountDto extends BaseDto{
    @ApiModelProperty(value = "name")
    private Integer value;

    @ApiModelProperty(value = "value")
    private String name;
}
