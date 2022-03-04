package com.sky.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sky.domain.SysSmsLog;
import com.sky.vo.DataVo;

import java.util.List;

/**
 * @author sky
 * @create 2021-09-20 18:48
 */
public interface SysSmsLogMapper extends BaseMapper<SysSmsLog> {
    // 获取本周每日发送信息次数
    List<DataVo> getSmsWeekData();
}