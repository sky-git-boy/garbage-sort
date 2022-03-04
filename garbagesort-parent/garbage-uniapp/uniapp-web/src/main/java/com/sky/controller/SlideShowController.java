package com.sky.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.sky.domain.SlideShow;
import com.sky.service.SlideShowServiceTwo;
import com.sky.vo.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 轮播图
 * @author sky
 * @create 2021-09-09 9:25
 */
@RestController
@RequestMapping("/slideShow")
@Api(tags = {"轮播图控制层"})
public class SlideShowController {

    @Autowired
    private SlideShowServiceTwo slideShowService;

    @GetMapping
    @ApiOperation("轮播图展示")
    public AjaxResult show() {
        List<SlideShow> list = slideShowService.showSlide();
        return AjaxResult.success(list);
    }
}

