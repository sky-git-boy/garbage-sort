package com.sky.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.sky.vo.AjaxResult;

/**
 * @author sky
 * @create 2021-06-06 15:15
 */
@DefaultProperties(defaultFallback = "fallback")
public class BaseController {
    /**
     * 如远程服务不可用，或者出现异常，回调的方法
     */
    public AjaxResult fallback() {
        return AjaxResult.toAjax(-1);
    }
}
