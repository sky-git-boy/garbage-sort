package com.sky.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sky.domain.Notice;
import com.sky.domain.OperLog;
import com.sky.dto.SysSmsLogDto;
import com.sky.vo.DataGridView;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.domain.SysSmsLog;
import com.sky.mapper.SysSmsLogMapper;
import com.sky.service.SysSmsLogService;

import java.util.Arrays;

/**
 * @author sky
 * @create 2021-09-20 18:48
 */
@Service
public class SysSmsLogServiceImpl extends ServiceImpl<SysSmsLogMapper, SysSmsLog> implements SysSmsLogService {

    @Autowired
    private SysSmsLogMapper smsMapper;

    @Override
    public DataGridView listSmsPage(SysSmsLogDto smsDto) {
        Page<SysSmsLog> page = new Page<>(smsDto.getPageNum(), smsDto.getPageSize());
        QueryWrapper<SysSmsLog> qw = new QueryWrapper<>();
        // 查询条件
        qw.like(StringUtils.isNotBlank(smsDto.getMobile()), "mobile", smsDto.getMobile());
        qw.eq(StringUtils.isNotBlank(smsDto.getStatus()), "status", smsDto.getStatus());
        qw.ge(null != smsDto.getBeginTime(), "create_time", smsDto.getBeginTime());
        qw.le(null != smsDto.getEndTime(), "create_time", smsDto.getEndTime());
        qw.orderByDesc("create_time");
        this.smsMapper.selectPage(page, qw);
        return new DataGridView(page.getTotal(), page.getRecords());
    }

    @Override
    public int deleteSmsByIds(Long[] ids) {
        if(ids != null && ids.length > 0) {
            return this.smsMapper.deleteBatchIds(Arrays.asList(ids));
        }
        return 0;
    }
}
