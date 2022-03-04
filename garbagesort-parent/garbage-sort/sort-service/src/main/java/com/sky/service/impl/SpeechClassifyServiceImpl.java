package com.sky.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sky.domain.SpeechClassify;
import com.sky.dto.SpeechClassifyDto;
import com.sky.mapper.SpeechClassifyMapper;
import com.sky.vo.DataGridView;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Service;

import com.sky.service.SpeechClassifyService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author sky
 * @create 2021-09-07 10:49
 */
@Service
public class SpeechClassifyServiceImpl implements SpeechClassifyService {

    @Autowired
    private SpeechClassifyMapper speechClassifyMapper;

    @Override
    public DataGridView listPage(SpeechClassifyDto speechClassifyDto) {
        QueryWrapper<SpeechClassify> qw = new QueryWrapper<>();
        Page<SpeechClassify> page = new Page<>(speechClassifyDto.getPageNum(), speechClassifyDto.getPageSize());
        qw.eq(StringUtils.isNotBlank(speechClassifyDto.getUserName()), "user_name",speechClassifyDto.getUserName());
        qw.like(StringUtils.isNotBlank(speechClassifyDto.getOneKeyword()), "one_keyword",speechClassifyDto.getOneKeyword());
        qw.ge(null != speechClassifyDto.getBeginTime(), "times", speechClassifyDto.getBeginTime());
        qw.le(null != speechClassifyDto.getEndTime(), "times", speechClassifyDto.getEndTime());
        qw.orderByDesc("times");
        this.speechClassifyMapper.selectPage(page, qw);
        return new DataGridView(page.getTotal(), page.getRecords());
    }
}
