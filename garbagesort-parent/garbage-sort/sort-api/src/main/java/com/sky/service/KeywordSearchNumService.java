package com.sky.service;


import java.util.List;

/**
 * @author sky
 * @create 2021-09-07 10:49
 */
public interface KeywordSearchNumService {

    List<Object[]> keywordSearchNum();

    // 获取本周每日查询次数
    int[] getSearchWeekData();

    int searchCount();
}
