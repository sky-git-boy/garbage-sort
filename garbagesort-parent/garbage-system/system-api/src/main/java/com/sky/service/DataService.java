package com.sky.service;

import java.util.List;

/**
 * @author sky
 * @create 2021-09-20 20:42
 */
public interface DataService {
    // 获取本周每天的相应数据
    List<int[]> getWeekDayData();

    List<Integer> getCount();
}
