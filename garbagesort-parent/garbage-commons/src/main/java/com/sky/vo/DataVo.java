package com.sky.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author sky
 * @create 2021-09-20 20:21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataVo implements Serializable {
    private Integer weekDay;
    private Integer num;
}
