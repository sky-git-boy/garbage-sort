package com.sky.service.impl;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.domain.KeywordResult;
import com.sky.domain.KeywordSearchNum;
import com.sky.domain.QuestionBank;
import com.sky.domain.SpeechClassify;
import com.sky.mapper.SpeechClassifyMapper;
import com.sky.service.KeywordResultServiceTwo;
import com.sky.service.KeywordSearchNumServiceTwo;
import com.sky.service.SpeechClassifyServiceTwo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author sky
 * @create 2021-09-08 8:25
 */
@Service
public class SpeechClassifyServiceTwoImpl extends ServiceImpl<SpeechClassifyMapper, SpeechClassify> implements SpeechClassifyServiceTwo {

    @Autowired
    KeywordResultServiceTwo krService;
    @Autowired
    KeywordSearchNumServiceTwo ksnService;

    @Override
    @Transactional
    public void speechHandle(String path, String speechResult, List<QuestionBank> list, String keyword, QuestionBank questionBankOne) {
        SpeechClassify speechClassify = SpeechClassify.builder()
                .oneKeyword(keyword)
                .oneResult(JSON.toJSONString(questionBankOne))
                .allKeyword(speechResult)
                .allResult(JSON.toJSONString(list))
                .userName("SKY")
                .filepath(path).build();
        this.save(speechClassify);

        krService.save(KeywordResult.builder().keyword(keyword).result(JSON.toJSONString(list)).build());

        KeywordSearchNum keywordNum = ksnService.getOne(new LambdaQueryWrapper<KeywordSearchNum>().eq(KeywordSearchNum::getKeyword, keyword));

        if (StringUtils.isEmpty(keywordNum)) {
            keywordNum = KeywordSearchNum.builder().keyword(keyword).num(1).build();
        } else {
            keywordNum.setNum(keywordNum.getNum() + 1);
        }
        ksnService.saveOrUpdate(keywordNum);
    }
}
