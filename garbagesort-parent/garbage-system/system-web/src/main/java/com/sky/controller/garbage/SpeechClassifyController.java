package com.sky.controller.garbage;

import com.sky.controller.BaseController;
import com.sky.dto.SpeechClassifyDto;
import com.sky.service.SpeechClassifyService;
import com.sky.vo.AjaxResult;
import com.sky.vo.DataGridView;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sky
 * @create 2021-09-18 22:53
 */
@RestController
@RequestMapping("/recognition/speech")
public class SpeechClassifyController extends BaseController {
    @Reference
    private SpeechClassifyService speechClassifyService;

    // 分页查询
    @GetMapping("/listSpeechForPage")
    public AjaxResult listForPage(SpeechClassifyDto speechClassifyDto) {
        DataGridView dataGridView = speechClassifyService.listPage(speechClassifyDto);
        return AjaxResult.success("查询成功", dataGridView.getData(), dataGridView.getTotal());
    }
}
