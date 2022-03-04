package com.sky.controller.system;

import cn.hutool.core.date.DateUtil;
import com.aliyun.oss.OSS;
import com.sky.vo.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 完成文件上传
 * @author sky
 * @create 2021-08-23 14:51
 */
@RestController
public class FileController {

    @Value("${aliyun.bucketName}")
    private String bucketName;

    @Value("${aliyun.endpoint}")
    private String endPoint;

    @Autowired
    private OSS ossClient;

    @PostMapping("/upload/image")
    public AjaxResult fileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        /**
         * 1 bucketName
         * 2 文件的名称 日期 +原始的文件名（uuid）来做
         * 3 文件的输入流
         * */
        // 2020-10-10->2020/10/10/xxx.jpg
        String fileName = DateUtil.today().replaceAll("-", "/") + "/" + file.getOriginalFilename();
        ossClient.putObject(bucketName, fileName, file.getInputStream());
        // https://coin-exchange-imgs.oss-cn-beijing.aliyuncs.com/2020/10/10/xxx.jpg
        // https://coin-exchange-imgs.oss-cn-beijing.aliyuncs.com/2020/10/10/banner9.jpg
        return AjaxResult.success("https://" + bucketName + "." + endPoint + "/" + fileName); //能使用浏览器访问到文件路径http://xxx.com/路径
    }

}
