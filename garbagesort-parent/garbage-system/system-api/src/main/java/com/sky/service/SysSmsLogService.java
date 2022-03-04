package com.sky.service;

import com.sky.domain.SysSmsLog;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sky.dto.SysSmsLogDto;
import com.sky.vo.DataGridView;

/**
 * @author sky
 * @create 2021-09-20 18:48
 */
public interface SysSmsLogService extends IService<SysSmsLog> {
    // 分页查询
    DataGridView listSmsPage(SysSmsLogDto smsDto);

    // 批量删除
    int deleteSmsByIds(Long[] ids);
}
