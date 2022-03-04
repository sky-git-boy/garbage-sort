package com.sky.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.domain.KeywordSearchNum;
import com.sky.mapper.KeywordSearchNumMapper;
import com.sky.service.KeywordSearchNumServiceTwo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author sky
 * @create 2021-09-08 8:25
 */
@Service
public class KeywordSearchNumServiceTwoImpl extends ServiceImpl<KeywordSearchNumMapper, KeywordSearchNum> implements KeywordSearchNumServiceTwo {

    /**
     * 获取前十的热门搜索
     */
    @Override
    public List<KeywordSearchNum> getTop() {
        return list(
                new LambdaQueryWrapper<KeywordSearchNum>()
                        .orderByDesc(KeywordSearchNum::getNum)
                        .last("limit 10")
        );
    }
}
