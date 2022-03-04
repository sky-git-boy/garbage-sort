package com.sky.service;


import com.sky.dto.ImageClassifyDto;
import com.sky.vo.DataGridView;

/**
 * @author sky
 * @create 2021-09-07 10:49
 */
public interface ImageClassifyService {

    // 分页查询
    DataGridView listPage(ImageClassifyDto imageClassifyDto);
}
