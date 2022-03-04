package com.sky.service;

import com.sky.domain.SlideShow;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author sky
 * @create 2021-09-08 8:25
 */
public interface SlideShowServiceTwo extends IService<SlideShow> {

    /**
     * 轮播图展示
     */
    List<SlideShow> showSlide();
}
