package com.sky.service;

import com.sky.domain.QuestionBank;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * @author sky
 * @create 2021-09-08 8:25
 */
public interface QuestionBankServiceTwo extends IService<QuestionBank> {

    /**
     * 通过类型查询垃圾列表
     */
    List<QuestionBank> garbageList(String garbageType);

    /**
     * 通过名称模糊查询垃圾
     */
    List<QuestionBank> queryByLikeName(String name);

    /**
     * 通过名称精准查询垃圾
     */
    Map<String, Object> queryByName(String name);

    /**
     * 获取随机个num的QuestionBank对象
     */
    List<QuestionBank> randNum(Integer num);
}
