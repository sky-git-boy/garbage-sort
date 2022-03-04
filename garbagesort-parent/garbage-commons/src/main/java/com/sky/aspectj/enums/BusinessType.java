package com.sky.aspectj.enums;

/**
 * 业务操作类型
 * @author sky
 * @create 2021-05-24 20:02
 */
public enum BusinessType {

    /**
     * 其他
     */
    OTHER,

    /**
     * 新增
     */
    INSERT,

    /**
     * 删除
     */
    DELETE,

    /**
     * 修改
     */
    UPDATE,

    /**
     * 授权
     */
    GRANT,

    /**
     * 导出
     */
    EXPORT,

    /**
     * 导入
     */
    IMPORT,

    /**
     * 强退
     */
    FORCE,

    /**
     * 生成代码
     */
    GENCODE,

    /**
     * 清空数据
     */
    CLEAN
}
