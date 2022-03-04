package com.sky.service.impl;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.domain.KeywordResult;
import com.sky.domain.KeywordSearchNum;
import com.sky.domain.QuestionBank;
import com.sky.mapper.QuestionBankMapper;
import com.sky.service.KeywordResultServiceTwo;
import com.sky.service.KeywordSearchNumServiceTwo;
import com.sky.service.QuestionBankServiceTwo;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sky
 * @create 2021-09-08 8:25
 */
@Service
@Log4j2
public class QuestionBankServiceTwoImpl extends ServiceImpl<QuestionBankMapper, QuestionBank> implements QuestionBankServiceTwo {

    @Autowired
    private KeywordSearchNumServiceTwo keywordSearchNumService;

    @Autowired
    private KeywordResultServiceTwo keywordResultService;

    @Autowired
    private QuestionBankMapper questionBankMapper;

    @Override
    public List<QuestionBank> garbageList(String garbageType) {
        return list(
                new LambdaQueryWrapper<QuestionBank>()
                        .eq(QuestionBank::getGarbageType, garbageType)
        );
    }

    @Override
    public List<QuestionBank> queryByLikeName(String name) {
        return list(
                new LambdaQueryWrapper<QuestionBank>()
                        .like(QuestionBank::getGarbageName, name)
        );
    }

    @Override
    public Map<String, Object> queryByName(String name) {
        if (StringUtils.isEmpty(name)) {
            return null;
        }
        // 查询垃圾
        QuestionBank questionBank = getOne(new LambdaQueryWrapper<QuestionBank>().eq(QuestionBank::getGarbageName, name));
        List<QuestionBank> list = list(new LambdaQueryWrapper<QuestionBank>().like(QuestionBank::getGarbageName, name));
        KeywordResult keywordResult = KeywordResult.builder().keyword(name).result(JSON.toJSONString(questionBank)).build();

        if (!StringUtils.isEmpty(questionBank)) {
            KeywordSearchNum keywordSearchNum = this.keywordSearchNumService.getOne(new LambdaQueryWrapper<KeywordSearchNum>().eq(KeywordSearchNum::getKeyword, name));
            // 修改垃圾查询次数
            if (StringUtils.isEmpty(keywordSearchNum)) {
                keywordSearchNum = KeywordSearchNum.builder().keyword(name).num(1).build();
            } else {
                keywordSearchNum.setNum(keywordSearchNum.getNum() + 1);
            }
            keywordSearchNumService.saveOrUpdate(keywordSearchNum);
        }

        this.keywordResultService.save(keywordResult);

        // 构造返回对象
        Map<String, Object> map = new HashMap<>();
        map.put("questionBank", questionBank);
        map.put("results", list);

        return map;
    }

    @Override
    public List<QuestionBank> randNum(Integer num) {
        return this.questionBankMapper.randNum(num);
    }
}
