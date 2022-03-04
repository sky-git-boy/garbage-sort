package com.sky.constants;

/**
 * 系统使用的常量
 * @author sky
 * @create 2021-05-18 18:46
 */
public interface Constants {
    /**
     * 令牌
     */
    String TOKEN = "token";

    /**
     * 系统用户类型
     */
    String USER_ADMIN = "0";
    String USER_NORMAL = "1";

    /**
     * 返回检验码
     */
    String UNIQUE = "0";
    String NOT_UNIQUE = "1";

    /**
     * 有效状态
     */
    String STATUS_TRUE = "0";
    String STATUS_FALSE = "1";

    /**
     * 删除状态
     */
    String DEL_FALSE = "0";
    String DEL_TRUE = "1";

    /**
     * 菜单类型
     */
    String MENU_TYPE_M = "M";
    String MENU_TYPE_C = "C";
    String MENU_TYPE_F = "F";

    /**
     * 入库单状态 1未提交2待审核 3审核通过 4审核失败 5作废 6 入库成功
     */
    String STOCK_PURCHASE_STATUS_1 = "1";
    String STOCK_PURCHASE_STATUS_2 = "2";
    String STOCK_PURCHASE_STATUS_3 = "3";
    String STOCK_PURCHASE_STATUS_4 = "4";
    String STOCK_PURCHASE_STATUS_5 = "5";
    String STOCK_PURCHASE_STATUS_6 = "6";

    /**
     * 入库状态
     */
    String STOCK_STORAGE_0 = "0";
    String STOCK_STORAGE_1 = "1";

    /**
     * 默认预警值
     */
    Long DEFAULT_WARNING = 50L;

    /**
     * 排班状态
     */
    String SCHEDULING_FLAG_TRUE = "0";
    String SCHEDULING_FLAG_FALSE = "1";

    /**
     * 是否完善信息
     */
    String IS_FINAL_FALSE = "0";
    String IS_FINAL_TRUE = "1";

    /**
     * 挂号单状态
     */
    String REG_STATUS_0 = "0"; //待支付
    String REG_STATUS_1 = "1"; //待就诊
    String REG_STATUS_2 = "2"; //就诊中
    String REG_STATUS_3 = "3"; //就诊完成
    String REG_STATUS_4 = "4"; //已退号
    String REG_STATUS_5 = "5"; //已作废

    /**
     * 处方类型
     */
    String CO_TYPE_MEDICINES = "0";
    String CO_TYPE_CHECK = "1";

    /**
     * 支付单状态状态，0未支付,1已支付，2支付超时
     */
    String ORDER_STATUS_0 = "0";
    String ORDER_STATUS_1 = "1";
    String ORDER_STATUS_2 = "2";

    /**
     * 订单子项目支付状态
     * 0未支付，1已支付，2，已退费  3，已完成
     */
    String ORDER_DETAILS_STATUS_0 = "0";
    String ORDER_DETAILS_STATUS_1 = "1";
    String ORDER_DETAILS_STATUS_2 = "2";
    String ORDER_DETAILS_STATUS_3 = "3";

    /**
     * 检查状态  0 检查中   1检查完成
     */
    String RESULT_STATUS_0 = "0";
    String RESULT_STATUS_1 = "1";

    /**
     * 退费单状态，订单状态0未退费  1 退费成功 2退费失败
     */
    String ORDER_BACKFEE_STATUS_0 = "0";
    String ORDER_BACKFEE_STATUS_1 = "1";
    String ORDER_BACKFEE_STATUS_2 = "2";
    /**
     * 支付类型
     */
    String PAY_TYPE_0 = "0";//现金
    String PAY_TYPE_1 = "1";//支付宝

    /**
     * redis的字典前缀
     */
    String DICT_REDIS_PROFIX="dict:";

    /**
     * 登陆状态  0 成功  1失败
     */
    String LOGIN_SUCCESS = "0";
    String LOGIN_ERROR = "1";

    /**
     * 登陆类型0系统用户1患者用户
     */
    String LOGIN_TYPE_SYSTEM = "0" ;
    String LOGIN_TYPE_PATIENT = "1" ;

    /**
     * ID 前缀
     */
    String ID_PROFIX_CG="CG";
}
