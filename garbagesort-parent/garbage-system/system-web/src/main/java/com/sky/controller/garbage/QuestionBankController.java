package com.sky.controller.garbage;

import com.sky.dto.QuestionBankDto;
import com.sky.service.QuestionBankService;
import com.sky.vo.AjaxResult;
import com.sky.vo.DataGridView;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/garbage/question")
public class QuestionBankController {
    @Reference
    private QuestionBankService questionBankService;

    /**
     * 分页查询
     */
    @GetMapping("/listQuest")
    public AjaxResult selectQuestionBankList(QuestionBankDto questionBankDto){
        DataGridView gridView = this.questionBankService.selectQuestionBankList(questionBankDto);
        return AjaxResult.success("查询成功", gridView.getData(), gridView.getTotal());
    }

    /**
     * 根据ID查找
     */
    @GetMapping(value = "/selectQuestionBankById/{questionId}")
    public AjaxResult selectQuestionBankById(@PathVariable("questionId") Integer questionId){
        return AjaxResult.success(questionBankService.selectQuestionById(questionId));
    }

    /**
     * 添加
     */
    @PostMapping("/addQuestion")
    public AjaxResult addQuestion(@Validated QuestionBankDto questionBankDto){
        return AjaxResult.toAjax(questionBankService.insertQuestionBank(questionBankDto));
    }

    /**
     * 修改
     */
    @PutMapping("/updateQuestion")
    public AjaxResult updateQuestion(@Validated QuestionBankDto questionBankDto){
        return AjaxResult.toAjax(this.questionBankService.updateQuestionBank(questionBankDto));
    }

    /**
     * 删除
     */
    @DeleteMapping("/deleteQuestion/{questionIds}")
    public AjaxResult deleteQuestion(@PathVariable Long[] questionIds){
        return AjaxResult.toAjax(questionBankService.deleteQuestionBank(questionIds));
    }

    /**
     * 查找不同类型搜索次数
     */
    @GetMapping("/selectType")
    public AjaxResult selectType(){
        return AjaxResult.success(questionBankService.selectType());
    }
}
