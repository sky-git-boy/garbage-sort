package com.sky.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sky.mapper.KeywordResultMapper;
import com.sky.vo.DataVo;
import org.apache.dubbo.config.annotation.Service;

import com.sky.service.KeywordResultService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author sky
 * @create 2021-09-07 10:49
 */
@Service
public class KeywordResultServiceImpl implements KeywordResultService {

    @Autowired
    private KeywordResultMapper mapper;

    @Override
    public int count() {
        return this.mapper.selectCount(new QueryWrapper<>());
    }

    @Override
    public int[] getSearchWeekData() {
        List<DataVo> data = mapper.getSearchWeekData();
        int[] res = new int[]{0, 0, 0, 0, 0, 0, 0};
        for (DataVo dataVo : data) {
            res[dataVo.getWeekDay()] = dataVo.getNum();
        }
        return res;
    }
}
