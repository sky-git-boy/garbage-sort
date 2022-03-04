package com.sky.controller.garbage;

import com.sky.aspectj.annotation.Log;
import com.sky.aspectj.enums.BusinessType;
import com.sky.controller.BaseController;
import com.sky.dto.SlideShowDto;
import com.sky.service.SlideShowService;
import com.sky.vo.AjaxResult;
import com.sky.vo.DataGridView;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author sky
 * @create 2021-09-19 17:12
 */
@RestController
@RequestMapping("/garbage/slide")
public class SlideShowController extends BaseController {

    @Reference
    private SlideShowService slideShowService;

    // 分页查询
    @GetMapping("/listSlideForPage")
    public AjaxResult listForPage(SlideShowDto slideShowDto) {
        DataGridView dataGridView = slideShowService.listPage(slideShowDto);
        return AjaxResult.success("查询成功", dataGridView.getData(), dataGridView.getTotal());
    }

    /**
     * 添加
     */
    @PostMapping("addSlide")
    @Log(title = "添加轮播图", businessType = BusinessType.INSERT)
    public AjaxResult addNotice(SlideShowDto SlideShowDto) {
        return AjaxResult.toAjax(this.slideShowService.addSlide(SlideShowDto));
    }

    /**
     * 修改
     */
    @PutMapping("updateSlide")
    @Log(title = "修改轮播图", businessType = BusinessType.UPDATE)
    public AjaxResult updateNotice(SlideShowDto SlideShowDto) {
        return AjaxResult.toAjax(this.slideShowService.updateSlide(SlideShowDto));
    }

    /**
     * 删除
     */
    @DeleteMapping("deleteSlideByIds/{slideIds}")
    @Log(title = "删除轮播图", businessType = BusinessType.DELETE)
    public AjaxResult deleteNoticeByIds(@PathVariable @NotEmpty(message = "要删除的ID不能为空") Long[] slideIds) {
        return AjaxResult.toAjax(this.slideShowService.deleteSlideByIds(slideIds));
    }

    /**
     * 根据ID查询一个
     */
    @GetMapping("getSlideById/{slide}")
    public AjaxResult getSlideById(@PathVariable @NotNull(message = "轮播图Id") Long slide) {
        return AjaxResult.success(this.slideShowService.getOne(slide));
    }
}
