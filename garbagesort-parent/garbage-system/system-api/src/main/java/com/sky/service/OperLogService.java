package com.sky.service;

import com.sky.domain.OperLog;
import com.sky.dto.OperLogDto;
import com.sky.vo.DataGridView;

/**
@author sky
@create 2021-05-24 15:07
*/
public interface OperLogService {

    /**
     * 分页查询操作日志信息
     */
    DataGridView listForPage(OperLogDto operLogDto);

    /**
     * 批量删除操作日志
     * 按日志ID
     */
    int deleteOperLogByIds(Long[] infoIds);

    /**
     * 清空所有操作日志
     */
    int clearAllOperLog();

    /**
     * 插入操作日志
     */
    void insertOperLog(OperLog operLog);
}
