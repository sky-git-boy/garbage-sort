package com.sky.service;


/**
 * @author sky
 * @create 2021-09-07 10:49
 */
public interface KeywordResultService {

    int count();

    // 获取本周每日查询次数
    int[] getSearchWeekData();
}
