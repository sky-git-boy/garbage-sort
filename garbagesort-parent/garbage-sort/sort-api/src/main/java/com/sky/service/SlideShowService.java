package com.sky.service;


import com.sky.domain.SlideShow;
import com.sky.dto.SlideShowDto;
import com.sky.vo.DataGridView;

/**
 * @author sky
 * @create 2021-09-07 10:49
 */
public interface SlideShowService {

    // 分页查询
    DataGridView listPage(SlideShowDto slideShowDto);

    int addSlide(SlideShowDto slideShowDto);

    int updateSlide(SlideShowDto slideShowDto);

    int deleteSlideByIds(Long[] slideIds);

    SlideShow getOne(Long slide);
}
