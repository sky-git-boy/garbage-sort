package com.sky.service.impl;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.domain.ChallengeDetail;
import com.sky.domain.ChallengeResult;
import com.sky.mapper.ChallengeResultMapper;
import com.sky.service.ChallengeDetailServiceTwo;
import com.sky.service.ChallengeResultServiceTwo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sky
 * @create 2021-09-08 8:25
 */
@Service
public class ChallengeResultServiceTwoImpl extends ServiceImpl<ChallengeResultMapper, ChallengeResult> implements ChallengeResultServiceTwo {

    @Autowired
    private ChallengeDetailServiceTwo challengeDetailService;

    @Override
    public Map<String, Object> getChallengeResult(Map<String, Object> map) {
        // 获取分数和用户名
        Integer score = (Integer) map.get("score");
        String username = (String) map.get("username");
        // 获取题库详情列表
        List<LinkedHashMap> list = (List<LinkedHashMap>) map.get("list");


        // 构造题库信息列表
        List<ChallengeDetail> detailArrayList = new ArrayList<>();
        for (LinkedHashMap temp : list) {
            ChallengeDetail challengeDetail = ChallengeDetail.builder()
                    .questionId((Integer) temp.get("questionId"))
                    .garbageName((String) temp.get("garbageName"))
                    .garbageType((Integer) temp.get("garbageType"))
                    .selectedType((Integer) temp.get("selectedType")).build();
            detailArrayList.add(challengeDetail);
        }

        // 构造答题结果
        ChallengeResult challengeResult = ChallengeResult.builder().score(score).userName(username).result(JSON.toJSONString(detailArrayList)).build();

        // 保存答题结果
        this.save(challengeResult);

        // 保存答题信息
        this.challengeDetailService.saveBatch(detailArrayList);

        // 构造返回值
        map.clear();
        map.put("handle", "ok");
        return map;
    }
}
