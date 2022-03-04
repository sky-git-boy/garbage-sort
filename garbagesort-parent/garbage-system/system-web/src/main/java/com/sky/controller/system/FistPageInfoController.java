package com.sky.controller.system;

import com.sky.controller.BaseController;
import com.sky.service.*;
import com.sky.vo.AjaxResult;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @Description:
 * @Author: lwx
 * @Date: 2021/9/10 15:39
 */
@RestController
@RequestMapping("system/fistPageInfo")
public class FistPageInfoController extends BaseController {

    @Reference
    private KeywordSearchNumService keywordSearchNumService;

    @Reference
    private ChallengeDetailService challengeDetailService;

    @Reference
    private KeywordResultService keywordResultService;

    @Reference
    private QuestionBankService questionBankService;

    @Autowired
    private DataService dataService;

    @GetMapping("/hotKeyword")
    public AjaxResult keywordSearchNum() {
        return AjaxResult.success(keywordSearchNumService.keywordSearchNum());
    }

    @GetMapping("/Accuracy")
    public AjaxResult Accuracy() {
        return AjaxResult.success("成功了", challengeDetailService.challengeAccuracy());
    }

    /**
     * 本周的每天数据
     */
    @GetMapping("/weekDayData")
    public AjaxResult weekDayData() {
        List<int[]> res = dataService.getWeekDayData();
        res.add(keywordResultService.getSearchWeekData());
        return AjaxResult.success(res);
    }

    /**
     * 某一类型的总数
     */
    @GetMapping("/count")
    public AjaxResult count() {
        List<Integer> count = dataService.getCount();
        int searchCount = keywordResultService.count();
        int questionBankCount = questionBankService.questionBankCount();
        count.add(questionBankCount);
        count.add(searchCount);
        return AjaxResult.success(count);
    }
}

