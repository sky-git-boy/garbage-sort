package com.sky.service;


import com.sky.dto.SpeechClassifyDto;
import com.sky.vo.DataGridView;

/**
 * @author sky
 * @create 2021-09-07 10:49
 */
public interface SpeechClassifyService {

    // 分页查询
    DataGridView listPage(SpeechClassifyDto speechClassifyDto);
}
