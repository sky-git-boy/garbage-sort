package com.sky.service;

import com.sky.domain.ImageClassify;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sky.domain.QuestionBank;
import org.json.JSONObject;

import java.util.List;

/**
 * @author sky
 * @create 2021-09-08 8:25
 */
public interface ImageClassifyServiceTwo extends IService<ImageClassify> {

    void imageHandle(String filename, JSONObject res, JSONObject resultVo, List<QuestionBank> questionBanks, QuestionBank questionBankOne);

}
