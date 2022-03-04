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
 * @create 2021-09-08 8:30
 * 用户表
 */
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "com-sky-domain-User")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "`user`")
public class UserTwo extends BaseEntity {
    @TableId(value = "user_id", type = IdType.AUTO)
    @ApiModelProperty(value = "")
    private Integer userId;

    /**
     * 用户名称
     */
    @TableField(value = "nick_name")
    @ApiModelProperty(value = "用户名称")
    private String nickName;

    @TableField(value = "avatar_url")
    @ApiModelProperty(value = "")
    private String avatarUrl;

    @TableField(value = "use_language")
    @ApiModelProperty(value = "")
    private String useLanguage;

    @TableField(value = "gender")
    @ApiModelProperty(value = "")
    private Byte gender;

    @TableField(value = "country")
    @ApiModelProperty(value = "")
    private String country;

    @TableField(value = "province")
    @ApiModelProperty(value = "")
    private String province;

    @TableField(value = "city")
    @ApiModelProperty(value = "")
    private String city;
}