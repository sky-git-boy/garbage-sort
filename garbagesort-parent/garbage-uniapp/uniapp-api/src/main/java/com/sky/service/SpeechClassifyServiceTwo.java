package com.sky.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sky.domain.QuestionBank;
import com.sky.domain.SpeechClassify;

import java.util.List;

/**
 * @author sky
 * @create 2021-09-08 8:25
 */
public interface SpeechClassifyServiceTwo extends IService<SpeechClassify> {

    void speechHandle(String path, String speechResult, List<QuestionBank> list, String keyword, QuestionBank questionBankOne);
}
