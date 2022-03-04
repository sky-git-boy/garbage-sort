package com.sky.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sky.domain.QuestionBank;
import com.sky.dto.QuestionBankDto;
import com.sky.dto.SearchCountDto;
import com.sky.vo.DataGridView;

import java.util.List;

/**
 * @author sky
 * @create 2021-09-07 10:49
 */
public interface QuestionBankMapper extends BaseMapper<QuestionBank> {
    //查询问题库
    DataGridView selectQuestionBankList(QuestionBankDto questionBankDto);

    //查找不同种类垃圾搜索次数
    List<SearchCountDto> selectType();

    //根据ID查找
    QuestionBank selectQuestionById(Integer questionId);

    //更新题目信息
    int updateQuestionBank(QuestionBankDto questionBankDto);

    //增加题目
    int insertQuestionBank(QuestionBankDto questionBankDto);

    //删除题目
    int deleteQuestionBank(Long[] questionIds);
}