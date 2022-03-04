package com.sky.config.exception;

import com.sky.vo.AjaxResult;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 全局异常处理
 * @author sky
 * @create 2021-05-23 20:45
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 当系统出现 MethodArgumentNotValidException 这个异常时，会调用下面的方法
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public AjaxResult jsonErrorHandler(MethodArgumentNotValidException e) {
        return getAjaxResult(e.getBindingResult());
    }

    /**
     * 当系统出现 BindException 这个异常时，会调用下面的方法
     * @param e
     * @return
     */
    @ExceptionHandler(value = BindException.class)
    public AjaxResult jsonErrorHandlerForParams(BindException e) {
        return getAjaxResult(e.getBindingResult());
    }

    /**
     * 重新包装异常数据
     * @param bindingResult
     * @return
     */
    public AjaxResult getAjaxResult(BindingResult bindingResult) {
        List<Map<String, Object>> list = new ArrayList<>();
        List<ObjectError> errors = bindingResult.getAllErrors();
        for(ObjectError error : errors) {
            Map<String, Object> map = new HashMap<>();
            map.put("defaultMessage", error.getDefaultMessage());
            map.put("objectName", error.getObjectName());

            // 注意，这里面拿到具体的某一个属性
            FieldError fieldError = (FieldError) error;
            map.put("field", fieldError.getField());
            list.add(map);
        }
        return AjaxResult.fail("后端数据校验异常", list);
    }
}
