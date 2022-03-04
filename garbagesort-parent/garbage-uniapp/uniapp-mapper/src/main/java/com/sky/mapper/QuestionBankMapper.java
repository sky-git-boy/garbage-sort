package com.sky.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sky.domain.QuestionBank;

import java.util.List;

/**
 * @author sky
 * @create 2021-09-08 8:25
 */
public interface QuestionBankMapper extends BaseMapper<QuestionBank> {
    /**
     * 获取num个随机的QuestionBank对象
     */
    List<QuestionBank> randNum(Integer num);
}