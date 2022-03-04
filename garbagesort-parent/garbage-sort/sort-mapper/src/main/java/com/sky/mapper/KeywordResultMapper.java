package com.sky.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sky.domain.KeywordResult;
import com.sky.vo.DataVo;

import java.util.List;

/**
 * @author sky
 * @create 2021-09-07 10:49
 */
public interface KeywordResultMapper extends BaseMapper<KeywordResult> {

    List<DataVo> getSearchWeekData();
}