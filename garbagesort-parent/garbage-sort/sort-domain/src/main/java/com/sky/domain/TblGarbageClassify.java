package com.sky.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author sky
 * @create 2021-09-07 10:49
 */
@ApiModel(value = "com-sky-domain-TblGarbageClassify")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "tbl_garbage_classify")
public class TblGarbageClassify {
    /**
     * 垃圾分类主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "垃圾分类主键")
    private Integer id;

    /**
     * 垃圾名称
     */
    @TableField(value = "garbage_name")
    @ApiModelProperty(value = "垃圾名称")
    private String garbageName;

    /**
     * 1--干垃圾 2--湿垃圾 3--可回收物 4--有害垃圾 5--大件垃圾
     */
    @TableField(value = "garbage_type")
    @ApiModelProperty(value = "1--干垃圾 2--湿垃圾 3--可回收物 4--有害垃圾 5--大件垃圾")
    private Byte garbageType;

    /**
     * 解析
     */
    @TableField(value = "analysis")
    @ApiModelProperty(value = "解析")
    private String analysis;

    /**
     * 备注
     */
    @TableField(value = "remark")
    @ApiModelProperty(value = "备注")
    private String remark;
}