package com.sky.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sky.domain.SlideShow;
import com.sky.dto.SlideShowDto;
import com.sky.mapper.SlideShowMapper;
import com.sky.vo.DataGridView;
import org.apache.dubbo.config.annotation.Service;

import com.sky.service.SlideShowService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

/**
 * @author sky
 * @create 2021-09-07 10:49
 */
@Service
public class SlideShowServiceImpl implements SlideShowService {

    @Autowired
    private SlideShowMapper slideShowMapper;

    @Override
    public DataGridView listPage(SlideShowDto slideShowDto) {
        QueryWrapper<SlideShow> qw = new QueryWrapper<>();
        Page<SlideShow> page = new Page<>(slideShowDto.getPageNum(), slideShowDto.getPageSize());
        qw.eq(slideShowDto.getUserId() != null, "user_id", slideShowDto.getUserId());
        qw.orderByDesc("sort_id");
        this.slideShowMapper.selectPage(page, qw);
        return new DataGridView(page.getTotal(), page.getRecords());
    }

    @Override
    public int addSlide(SlideShowDto slideShowDto) {
        SlideShow slideShow = new SlideShow();
        BeanUtils.copyProperties(slideShowDto, slideShow);
        return this.slideShowMapper.insert(slideShow);
    }

    @Override
    public int updateSlide(SlideShowDto slideShowDto) {
        SlideShow slideShow = new SlideShow();
        BeanUtils.copyProperties(slideShowDto, slideShow);
        return this.slideShowMapper.updateById(slideShow);
    }

    @Override
    public int deleteSlideByIds(Long[] slideIds) {
        List<Long> ids = Arrays.asList(slideIds);
        if (ids.size() > 0) {
            return this.slideShowMapper.deleteBatchIds(ids);
        } else {
            return -1;
        }
    }

    @Override
    public SlideShow getOne(Long slide) {
        return this.slideShowMapper.selectById(slide);
    }
}
