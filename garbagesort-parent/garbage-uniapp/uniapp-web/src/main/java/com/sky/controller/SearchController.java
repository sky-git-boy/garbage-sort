package com.sky.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.sky.domain.KeywordSearchNum;
import com.sky.domain.QuestionBank;
import com.sky.service.KeywordSearchNumServiceTwo;
import com.sky.service.QuestionBankServiceTwo;
import com.sky.vo.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 搜索控制器
 * @author sky
 * @create 2021-09-09 9:25
 */
@RestController
@RequestMapping("/search")
@Api(tags = {"搜索控制器"})
public class SearchController {

    @Autowired
    private KeywordSearchNumServiceTwo service;

    @Autowired
    private QuestionBankServiceTwo questionBankService;

    @GetMapping("/top10")
    @ApiOperation("展示前十热门搜索")
    public AjaxResult top() {
        List<KeywordSearchNum> res = this.service.getTop();
        return AjaxResult.success(res);
    }

    @GetMapping("/name/{name}")
    @ApiOperation("按名字模糊查询")
    public AjaxResult searchGarbageByLikeName(@PathVariable String name) {
        List<QuestionBank> res = this.questionBankService.queryByLikeName(name);
        return AjaxResult.success(res);
    }

    @GetMapping("/uname/{name}")
    @ApiOperation("根据名称精准查询垃圾分类")
    public AjaxResult searchGarbageByName(@PathVariable String name) {
        Map<String, Object> res = this.questionBankService.queryByName(name);
        return AjaxResult.success(res);
    }
}
