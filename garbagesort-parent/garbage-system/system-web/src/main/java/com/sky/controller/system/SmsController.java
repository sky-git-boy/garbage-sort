package com.sky.controller.system;

import com.sky.aspectj.annotation.Log;
import com.sky.aspectj.enums.BusinessType;
import com.sky.controller.BaseController;
import com.sky.dto.SysSmsLogDto;
import com.sky.service.SysSmsLogService;
import com.sky.vo.AjaxResult;
import com.sky.vo.DataGridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author sky
 * @create 2021-09-20 18:57
 */
@RestController
@RequestMapping("system/sms")
public class SmsController extends BaseController {
    @Autowired
    private SysSmsLogService sysSmsLogService;

    /**
     * 分页查询
     */
    @GetMapping("listSmsForPage")
    public AjaxResult listSmsForPage(SysSmsLogDto smsDto){
        DataGridView gridView = this.sysSmsLogService.listSmsPage(smsDto);
        return AjaxResult.success("查询成功", gridView.getData(), gridView.getTotal());
    }

    /**
     * 根据ID查询一个
     */
    @GetMapping("getSmsById/{id}")
    public AjaxResult getSmsById(@PathVariable @Validated @NotNull(message = "ID不能为空") Long id) {
        return AjaxResult.success(this.sysSmsLogService.getById(id));
    }

    /**
     * 删除
     */
    @DeleteMapping("deleteSmsByIds/{ids}")
    @Log(title = "删除短信日志", businessType = BusinessType.DELETE)
    public AjaxResult deleteSmsByIds(@PathVariable @Validated @NotEmpty(message = "要删除的ID不能为空") Long[] ids) {
        return AjaxResult.toAjax(this.sysSmsLogService.deleteSmsByIds(ids));
    }
}
