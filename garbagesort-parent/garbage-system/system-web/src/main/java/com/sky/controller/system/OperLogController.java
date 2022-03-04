package com.sky.controller.system;

import com.sky.controller.BaseController;
import com.sky.dto.OperLogDto;
import com.sky.service.OperLogService;
import com.sky.vo.AjaxResult;
import com.sky.vo.DataGridView;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
 * @author sky
 * @create 2021-05-24 17:16
 */
@Log4j2
@RestController
@RequestMapping("system/operLog")
public class OperLogController extends BaseController {

    @Autowired
    private OperLogService operLogService;

    /**
     * 分页查询
     */
    @GetMapping("/listForPage")
    public AjaxResult listForPage(OperLogDto operLogDto) {
        DataGridView dataGridView = this.operLogService.listForPage(operLogDto);
        return AjaxResult.success("查询成功", dataGridView.getData(), dataGridView.getTotal());
    }


    /**
     * 根据ID删除操作日志信息
     */
    @DeleteMapping("/deleteOperLogByIds/{infoIds}")
    public AjaxResult deleteOperLogByIds(@PathVariable @Validated @NotNull(message = "删除ID不能为空") Long[] infoIds) {
        return AjaxResult.toAjax(this.operLogService.deleteOperLogByIds(infoIds));
    }

    /**
     * 清空所有操作日志
     */
    @DeleteMapping("/clearAllOperLog")
    public AjaxResult clearAllOperLog() {
        return AjaxResult.toAjax(this.operLogService.clearAllOperLog());
    }
}
