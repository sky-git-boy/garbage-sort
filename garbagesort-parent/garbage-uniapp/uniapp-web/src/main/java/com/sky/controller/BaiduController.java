package com.sky.controller;

import com.sky.common.baidu.BaiduService;
import com.sky.common.oss.OssService;
import com.sky.common.upload.UploadFileService;
import com.sky.service.impl.RecordUploadService;
import com.sky.utils.FileUtils;
import com.sky.vo.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

/**
 * 百度的api控制层
 * @author sky
 * @create 2021-09-12 9:25
 */
@Log4j2
@RestController
@RequestMapping("/baidu")
@Api(tags = {"百度api控制层"})
public class BaiduController {

    @Autowired
    private UploadFileService uploadFileService;

    @Autowired
    private BaiduService baiduService;

    @Autowired
    private OssService ossService;

    @Autowired
    private RecordUploadService uploadService;

    @PostMapping("/image")
    @ApiOperation(value = "图像识别")
    public AjaxResult uploadImage(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return AjaxResult.fail("文件为空");
        }
        // 上传文件
//        String path = uploadFileService.handleUploadFile(file);
        String path = ossService.fileUpload(file);
        // 对接百度api接口 - 实现图像识别
        Map<String, Object> map;
        try {
            map = baiduService.imageClassify(path);
        } catch (IOException e) {
            e.printStackTrace();
            return AjaxResult.fail("文件有问题");
        }
        return AjaxResult.success(map);
    }

    @PostMapping("/record")
    @ApiOperation(value = " 通过上传音频进行语音识别其垃圾分类")
    public AjaxResult uploadRecord(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return AjaxResult.fail("文件为空");
        }
        byte[] pcmBytes = null;
        try {
            pcmBytes = FileUtils.getInputStreamContent(file.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 上传文件到oss
        String path = ossService.fileUpload(file);
        // 上传到本地
        String s = uploadService.handleUploadFile(file);

        // 对接百度api接口 - 实现图像识别
        Map<String, Object> map;
        try {
            map = baiduService.speech(path, pcmBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.fail("文件有问题");
        }
        return AjaxResult.success(map);
    }
}
