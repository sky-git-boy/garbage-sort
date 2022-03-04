package com.sky.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.domain.ImageClassify;
import com.sky.domain.KeywordResult;
import com.sky.domain.KeywordSearchNum;
import com.sky.domain.QuestionBank;
import com.sky.mapper.ImageClassifyMapper;
import com.sky.service.ImageClassifyServiceTwo;
import com.sky.service.KeywordResultServiceTwo;
import com.sky.service.KeywordSearchNumServiceTwo;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author sky
 * @create 2021-09-08 8:25
 */
@Service
public class ImageClassifyServiceTwoImpl extends ServiceImpl<ImageClassifyMapper, ImageClassify> implements ImageClassifyServiceTwo {

    @Autowired
    KeywordResultServiceTwo krService;

    @Autowired
    KeywordSearchNumServiceTwo ksnService;

    @Transactional
    @Override
    public void imageHandle(String filename, JSONObject res, JSONObject resultVo, List<QuestionBank> questionBanks, QuestionBank questionBankOne) {

        final JSONArray jsonArray = res.getJSONArray("result");

        StringBuilder allKeyword = new StringBuilder();
        for (int i = 0; i < jsonArray.length(); i++) {
            allKeyword.append(jsonArray.getJSONObject(i).getString("keyword")).append(",");
        }

        ImageClassify imageClassify = ImageClassify.builder()
                .oneKeyword(resultVo.getString("keyword"))
                .oneResult(JSON.toJSONString(questionBankOne))
                .allKeyword(allKeyword.toString())
                .allResult(JSON.toJSONString(questionBanks))
                .userName("sky")
                .filepath(filename).build();
        // 保存
        this.save(imageClassify);

        String name = resultVo.getString("keyword");

        // 保存关键词+结果记录
        krService.save(KeywordResult.builder().keyword(name).result(JSON.toJSONString(questionBanks)).build());

        KeywordSearchNum keywordNum = ksnService.getOne(new LambdaQueryWrapper<KeywordSearchNum>().eq(KeywordSearchNum::getKeyword, name));

        if (StringUtils.isEmpty(keywordNum)) {
            keywordNum = KeywordSearchNum.builder().keyword(name).num(1).build();
        } else {
            keywordNum.setNum(keywordNum.getNum() + 1);
        }

        // 保存搜索关键词+次数记录
        ksnService.saveOrUpdate(keywordNum);
    }
}
