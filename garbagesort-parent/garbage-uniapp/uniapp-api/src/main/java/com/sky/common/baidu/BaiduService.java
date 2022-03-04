package com.sky.common.baidu;

import com.alibaba.fastjson.JSON;
import com.baidu.aip.imageclassify.AipImageClassify;
import com.baidu.aip.speech.AipSpeech;
import com.baidu.aip.util.Util;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sky.domain.QuestionBank;
import com.sky.service.ImageClassifyServiceTwo;
import com.sky.service.QuestionBankServiceTwo;
import com.sky.service.SpeechClassifyServiceTwo;
import com.sky.utils.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 百度接口服务
 *
 * @author sky
 * @create 2021-09-12 19:47
 */
@Service
@EnableConfigurationProperties(BaiduConfig.class)
public class BaiduService {

    @Autowired
    private BaiduConfig baiduConfig;

    @Autowired
    QuestionBankServiceTwo qbService;

    @Autowired
    ImageClassifyServiceTwo imageClassifyService;

    @Autowired
    SpeechClassifyServiceTwo speechClassifyService;

    public Map<String, Object> imageClassify(String filename) throws IOException {
        // 初始化一个AipImageClassify
        AipImageClassify client = new AipImageClassify(baiduConfig.getAppid(), baiduConfig.getApikey(), baiduConfig.getSecretkey());

        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<>();

        // 将图片转为二进制流
        byte[] file = FileUtils.image2byte(filename);

        // 调用百度接口实现图像识别
        JSONObject res = client.advancedGeneral(file, options);

        // 获取图像识别的结果集
        JSONArray results = res.getJSONArray("result");
        // 获取第一个结果，可信度最高的
        JSONObject resultVo = results.getJSONObject(0);

        QuestionBank questionBankOne = null;
        List<QuestionBank> questionBanks = new ArrayList<>();

        // score  置信度，0-1
        if (resultVo.getDouble("score") > 0.7) {
            // 精确查询
            questionBankOne = qbService.getOne(
                    new LambdaQueryWrapper<QuestionBank>()
                            .eq(QuestionBank::getGarbageName, resultVo.getString("keyword"))
            );
            // 模糊查询
            questionBanks = qbService.list(
                    new LambdaQueryWrapper<QuestionBank>()
                            .like(QuestionBank::getGarbageName, resultVo.getString("keyword"))
            );
        }

        // 保存图像识别结果
        this.imageClassifyService.imageHandle(filename, res, resultVo, questionBanks, questionBankOne);

        // 构造返回对象
        Map<String, Object> map = new HashMap<>();
        map.put("results", questionBanks);
        map.put("keyword", resultVo.getString("keyword"));
        map.put("response", res.toString());
        map.put("uni", questionBankOne);
        return map;
    }

    // 语音识别
    public Map<String, Object> speech(String path, byte[] bytes) {
        // 初始化一个 AipSpeech
        AipSpeech speech = new AipSpeech(baiduConfig.getAppid(), baiduConfig.getApikey(), baiduConfig.getSecretkey());

        // 可选：设置网络连接参数
        speech.setConnectionTimeoutInMillis(2000);
        speech.setSocketTimeoutInMillis(60000);

        String speechResult;
        List<QuestionBank> results = null;
        String keyword;

        // 获取文件二进制流
//        byte[] bytes = new byte[0];
//        try {
//            bytes = FileUtils.getFileContent(path);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        byte[] fileByte = null;
//        try {
//            fileByte = FileUtils.mp3Convertpcm(inputStream);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        // 调用接口实现语音识别
        JSONObject res = speech.asr(bytes, "pcm", 16000, null);

        if (null == res) {
            return null;
        }
        speechResult = res.toString();
        System.out.println(speechResult);
        com.alibaba.fastjson.JSONObject object = JSON.parseObject(speechResult);
        com.alibaba.fastjson.JSONArray result = object.getJSONArray("result");

        String[] split = result.getString(0).split("。");

        if (split.length == 0) throw new RuntimeException("语音识别有误");

        // 获取关键字
        keyword = split[0];

        QuestionBank questionBankOne = null;

        if (!keyword.equals("")) {
            // 查询结果
            results = qbService.list(new LambdaQueryWrapper<QuestionBank>().like(QuestionBank::getGarbageName, keyword));

            questionBankOne = qbService.getOne(new LambdaQueryWrapper<QuestionBank>().eq(QuestionBank::getGarbageName, keyword));
        }

        // 保存语音识别结果
        this.speechClassifyService.speechHandle(path, speechResult, results, keyword, questionBankOne);

        // 构造返回对象
        Map<String, Object> map = new HashMap<>();
        map.put("keyword", keyword);
        map.put("results", results);
        map.put("response", speechResult);
        map.put("uni", questionBankOne);
        return map;
    }
}
