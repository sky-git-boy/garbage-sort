package com.sky.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sky.mapper.ChallengeDetailMapper;
import org.apache.dubbo.config.annotation.Service;

import com.sky.service.ChallengeDetailService;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.DecimalFormat;

/**
 * @author sky
 * @create 2021-09-07 10:49
 */
@Service
public class ChallengeDetailServiceImpl implements ChallengeDetailService {
    @Autowired
    ChallengeDetailMapper challengeDetailMapper;

    @Override
    public String challengeAccuracy() {
        double allNums = 0;
        double num = 0;
        QueryWrapper qw = new QueryWrapper();
        allNums = challengeDetailMapper.selectCount(qw);
        qw.eq("whether", 1);
        num = challengeDetailMapper.selectCount(qw);

        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(num / allNums * 100);
    }
}
