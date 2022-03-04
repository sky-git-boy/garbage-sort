package com.sky.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sky.domain.KeywordSearchNum;
import com.sky.mapper.KeywordSearchNumMapper;
import com.sky.vo.DataVo;
import org.apache.dubbo.config.annotation.Service;

import com.sky.service.KeywordSearchNumService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sky
 * @create 2021-09-07 10:49
 */
@Service
public class KeywordSearchNumServiceImpl implements KeywordSearchNumService {

    @Autowired
    KeywordSearchNumMapper mapper;

    @Override
    public List<Object[]> keywordSearchNum() {
        QueryWrapper<KeywordSearchNum> qw = new QueryWrapper<>();
        qw.orderByDesc("num");
        qw.last("limit 5");

        List<KeywordSearchNum> keywordSearchNums = mapper.selectList(qw);

        Object[] keyWord = new Object[]{"", "", "", "", ""};
        Object[] value = new Object[]{0, 0, 0, 0, 0};

        int i =0;
        for (KeywordSearchNum keywordSearchNum : keywordSearchNums) {
            keyWord[i] = keywordSearchNum.getKeyword();
            value[i] = keywordSearchNum.getNum();
            i++;
        }

        List<Object[]> res = new ArrayList<>();
        res.add(keyWord);
        res.add(value);

        return res;
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

    @Override
    public int searchCount() {
        return this.mapper.selectCount(new QueryWrapper<>());
    }
}
