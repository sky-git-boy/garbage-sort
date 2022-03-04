package com.sky.service;

import com.sky.domain.KeywordSearchNum;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author sky
 * @create 2021-09-08 8:25
 */
public interface KeywordSearchNumServiceTwo extends IService<KeywordSearchNum> {

    /**
     * 获取前十热门搜索
     */
    List<KeywordSearchNum> getTop();
}
