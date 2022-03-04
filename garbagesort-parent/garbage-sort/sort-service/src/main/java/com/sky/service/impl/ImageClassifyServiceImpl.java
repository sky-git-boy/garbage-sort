package com.sky.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sky.domain.ImageClassify;
import com.sky.domain.SpeechClassify;
import com.sky.dto.ImageClassifyDto;
import com.sky.mapper.ImageClassifyMapper;
import com.sky.vo.DataGridView;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Service;
import com.sky.service.ImageClassifyService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author sky
 * @create 2021-09-07 10:49
 */
@Service
public class ImageClassifyServiceImpl implements ImageClassifyService {

    @Autowired
    private ImageClassifyMapper imageClassifyMapper;


    @Override
    public DataGridView listPage(ImageClassifyDto imageClassifyDto) {
        QueryWrapper<ImageClassify> qw = new QueryWrapper<>();
        Page<ImageClassify> page = new Page<>(imageClassifyDto.getPageNum(), imageClassifyDto.getPageSize());
        qw.eq(StringUtils.isNotBlank(imageClassifyDto.getUserName()), "user_name",imageClassifyDto.getUserName());
        qw.like(StringUtils.isNotBlank(imageClassifyDto.getOneKeyword()), "one_keyword",imageClassifyDto.getOneKeyword());
        qw.ge(null != imageClassifyDto.getBeginTime(), "times", imageClassifyDto.getBeginTime());
        qw.le(null != imageClassifyDto.getEndTime(), "times", imageClassifyDto.getEndTime());
        qw.orderByDesc("times");
        this.imageClassifyMapper.selectPage(page, qw);
        return new DataGridView(page.getTotal(), page.getRecords());
    }
}
