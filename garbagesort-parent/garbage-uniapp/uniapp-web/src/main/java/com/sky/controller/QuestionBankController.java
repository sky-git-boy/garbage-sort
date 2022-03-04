package com.sky.controller;


import com.sky.domain.QuestionBank;
import com.sky.service.QuestionBankServiceTwo;
import com.sky.vo.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 题库及垃圾分类控制器
 * @author sky
 * @create 2021-09-09 9:25
 */
@RestController
@RequestMapping("/qb")
@Api(tags = {"题库及垃圾分类访问接口"})
public class QuestionBankController {
    @Autowired
    private QuestionBankServiceTwo questionBankService;

    @GetMapping("/type/{type}")
    @ApiOperation("垃圾分类列表按垃圾类型查询")
    public AjaxResult garbageList(@PathVariable("type") String garbageType) {
        List<QuestionBank> res = this.questionBankService.garbageList(garbageType);
        return AjaxResult.success(res);
    }

    @GetMapping("/randNum")
    @ApiOperation("随机获取一个垃圾")
    public AjaxResult randOne(@RequestParam(required = false, name = "num", defaultValue = "1") Integer num) {
        List<QuestionBank> res = this.questionBankService.randNum(num);
        return AjaxResult.success(res);
    }
}

