package com.sky.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.constants.Constants;
import com.sky.domain.SlideShow;
import com.sky.mapper.SlideShowMapper;
import com.sky.service.SlideShowServiceTwo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author sky
 * @create 2021-09-08 8:25
 */
@Service
public class SlideShowServiceTwoImpl extends ServiceImpl<SlideShowMapper, SlideShow> implements SlideShowServiceTwo {

    /**
     * 轮播图展示
     */
    @Override
    public List<SlideShow> showSlide() {
        return list(
                new LambdaQueryWrapper<SlideShow>()
                        .eq(SlideShow::getUserId, Constants.STATUS_TRUE)
                        .orderByDesc(SlideShow::getSortId)
        );
    }
}
