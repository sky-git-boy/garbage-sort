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

import java.util.Date;

/**
 * @author com.sky
 * @create 2021-05-24 15:07
 * 操作日志记录
 */
@ApiModel(value = "com-sky-domain-OperLog")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_oper_log")
public class OperLog extends BaseEntity {
    /**
     * 日志主键
     */
    @TableId(value = "oper_id", type = IdType.AUTO)
    @ApiModelProperty(value = "日志主键")
    private Long operId;

    /**
     * 模块标题
     */
    @TableField(value = "title")
    @ApiModelProperty(value = "模块标题")
    private String title;

    /**
     * 业务类型（0其它 1新增 2修改 3删除）
     */
    @TableField(value = "business_type")
    @ApiModelProperty(value = "业务类型（0其它 1新增 2修改 3删除）")
    private String businessType;

    /**
     * 方法名称
     */
    @TableField(value = "method")
    @ApiModelProperty(value = "方法名称")
    private String method;

    /**
     * 请求方式
     */
    @TableField(value = "request_method")
    @ApiModelProperty(value = "请求方式")
    private String requestMethod;

    /**
     * 操作类别（0其它 1后台用户 2手机端用户）
     */
    @TableField(value = "operator_type")
    @ApiModelProperty(value = "操作类别（0其它 1后台用户 2手机端用户）")
    private Integer operatorType;

    /**
     * 操作人员
     */
    @TableField(value = "oper_name")
    @ApiModelProperty(value = "操作人员")
    private String operName;

    /**
     * 请求URL
     */
    @TableField(value = "oper_url")
    @ApiModelProperty(value = "请求URL")
    private String operUrl;

    /**
     * 主机地址
     */
    @TableField(value = "oper_ip")
    @ApiModelProperty(value = "主机地址")
    private String operIp;

    /**
     * 操作地点
     */
    @TableField(value = "oper_location")
    @ApiModelProperty(value = "操作地点")
    private String operLocation;

    /**
     * 请求参数
     */
    @TableField(value = "oper_param")
    @ApiModelProperty(value = "请求参数")
    private String operParam;

    /**
     * 返回参数
     */
    @TableField(value = "json_result")
    @ApiModelProperty(value = "返回参数")
    private String jsonResult;

    /**
     * 操作状态（0正常 1异常）
     */
    @TableField(value = "status")
    @ApiModelProperty(value = "操作状态（0正常 1异常）")
    private String status;

    /**
     * 错误消息
     */
    @TableField(value = "error_msg")
    @ApiModelProperty(value = "错误消息")
    private String errorMsg;

    /**
     * 操作时间
     */
    @TableField(value = "oper_time")
    @ApiModelProperty(value = "操作时间")
    private Date operTime;

    public static final String COL_OPER_ID = "oper_id";

    public static final String COL_TITLE = "title";

    public static final String COL_BUSINESS_TYPE = "business_type";

    public static final String COL_METHOD = "method";

    public static final String COL_REQUEST_METHOD = "request_method";

    public static final String COL_OPERATOR_TYPE = "operator_type";

    public static final String COL_OPER_NAME = "oper_name";

    public static final String COL_OPER_URL = "oper_url";

    public static final String COL_OPER_IP = "oper_ip";

    public static final String COL_OPER_LOCATION = "oper_location";

    public static final String COL_OPER_PARAM = "oper_param";

    public static final String COL_JSON_RESULT = "json_result";

    public static final String COL_STATUS = "status";

    public static final String COL_ERROR_MSG = "error_msg";

    public static final String COL_OPER_TIME = "oper_time";
}