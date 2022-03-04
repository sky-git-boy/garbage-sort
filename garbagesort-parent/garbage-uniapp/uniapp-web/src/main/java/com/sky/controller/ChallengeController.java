package com.sky.controller;


import com.sky.service.ChallengeResultServiceTwo;
import com.sky.vo.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


/**
 * 小测结果的统计
 * @author sky
 * @create 2021-09-09 9:25
 */
@RestController
@RequestMapping("/challenge")
@Api(tags = {"小测控制层"})
@Log4j2
public class ChallengeController {

    @Autowired
    private ChallengeResultServiceTwo challengeResultService;

    @PostMapping
    @ApiOperation("小测的结果统计")
    public AjaxResult challengeResult(@RequestBody Map<String, Object> map) {
        Map<String, Object> res = this.challengeResultService.getChallengeResult(map);
        return AjaxResult.success(res);
    }
}
