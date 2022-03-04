package com.sky.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sky.domain.KeywordSearchNum;
import com.sky.vo.DataVo;

import java.util.List;

/**
 * @author sky
 * @create 2021-09-07 10:49
 */
public interface KeywordSearchNumMapper extends BaseMapper<KeywordSearchNum> {
    // 获取本周每日查询次数
    List<DataVo> getSearchWeekData();
}