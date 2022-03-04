package com.sky.common.oss;

import cn.hutool.core.date.DateUtil;
import com.aliyun.oss.OSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 完成文件上传
 * @author sky
 * @create 2021-08-23 14:51
 */
@Service
public class OssService {

    @Value("${aliyun.bucketName}")
    private String bucketName;

    @Value("${aliyun.endpoint}")
    private String endPoint;

    @Autowired
    private OSS ossClient;

    public String fileUpload(MultipartFile file) {
        /**
         * 1 bucketName
         * 2 文件的名称 日期 +原始的文件名（uuid）来做
         * 3 文件的输入流
         * */
        // 2020-10-10->2020/10/10/xxx.jpg
        String fileName = DateUtil.today().replaceAll("-", "/") + "/" + file.getOriginalFilename();
        try {
            ossClient.putObject(bucketName, fileName, file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        // https://coin-exchange-imgs.oss-cn-beijing.aliyuncs.com/2020/10/10/xxx.jpg
        // https://coin-exchange-imgs.oss-cn-beijing.aliyuncs.com/2020/10/10/banner9.jpg
        return "https://" + bucketName + "." + endPoint + "/" + fileName; //能使用浏览器访问到文件路径http://xxx.com/路径
    }

}
