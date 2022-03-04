package com.sky.service;

import com.sky.domain.ChallengeResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * @author sky
 * @create 2021-09-08 8:25
 */
public interface ChallengeResultServiceTwo extends IService<ChallengeResult> {
    /**
     * 小测结果统计
     */
    Map<String, Object> getChallengeResult(Map<String, Object> map);
}
