package com.sky.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sky.domain.QuestionBank;
import com.sky.dto.QuestionBankDto;
import com.sky.dto.SearchCountDto;
import com.sky.mapper.QuestionBankMapper;
import com.sky.service.QuestionBankService;
import com.sky.vo.DataGridView;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author sky
 * @create 2021-09-07 10:49
 */
@Service
public class QuestionBankServiceImpl implements QuestionBankService {

    @Autowired
    private QuestionBankMapper questionBankMapper;


    @Override
    public DataGridView selectQuestionBankList(QuestionBankDto questionBankDto) {
        Page<QuestionBank> page = new Page<>(questionBankDto.getPageNum(), questionBankDto.getPageSize());
        QueryWrapper<QuestionBank> qw = new QueryWrapper<>();
//         查询条件
        qw.like(StringUtils.isNotBlank(questionBankDto.getGarbageName()), QuestionBank.COL_GARBAGE_NAME,questionBankDto.getGarbageName());
        qw.eq(StringUtils.isNotBlank(questionBankDto.getGarbageType()),QuestionBank.COL_GARBAGE_TYPE,questionBankDto.getGarbageType());
        this.questionBankMapper.selectPage(page,qw);
        return new DataGridView(page.getTotal(),page.getRecords());
    }

    @Override
    public QuestionBank selectQuestionById(Integer questionId) {
        return questionBankMapper.selectQuestionById(questionId);
    }

    @Override
    public int updateQuestionBank(QuestionBankDto questionBankDto) {
        QuestionBank questionBank  = new QuestionBank();
        BeanUtils.copyProperties(questionBankDto, questionBank);
        return this.questionBankMapper.updateById(questionBank);
    }

    @Override
    public int insertQuestionBank(QuestionBankDto questionBankDto) {
        return questionBankMapper.insertQuestionBank(questionBankDto);
    }

    @Override
    public int deleteQuestionBank(Long[] questionIds) {
        return questionBankMapper.deleteQuestionBank(questionIds);
    }

    @Override
    public List<SearchCountDto> selectType() {
        return questionBankMapper.selectType();
    }

    @Override
    public int questionBankCount() {
        return this.questionBankMapper.selectCount(new QueryWrapper<>());
    }
}
