package com.sky.service.impl;

import com.sky.utils.IdGeneratorSnowflake;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

/**
 * @author sky
 * @create 2021-10-27 20:01
 */
@Service
public class RecordUploadService {

    @Value("${upload.record.path}")
    String path;

    public String handleUploadFile(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        LocalDateTime now = LocalDateTime.now();
        File filePathIF = new File(path, String.valueOf(now.getYear()));
        if (!filePathIF.exists()) {
            filePathIF.mkdir();
            filePathIF = new File(filePathIF.getAbsolutePath(), String.valueOf(now.getMonthValue()));
            if (!filePathIF.exists()) {
                filePathIF.mkdir();
                filePathIF = new File(filePathIF.getAbsolutePath(), String.valueOf(now.getDayOfMonth()));
                if (!filePathIF.exists()) {
                    filePathIF.mkdir();
                }
            }
        }
        String[] split = fileName.split("\\.");
        String newFileName = IdGeneratorSnowflake.generatorIdWithProfix("record") + "." + split[split.length - 1];
        File dest = new File(filePathIF, newFileName);
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            throw new RuntimeException("文件处理失败");
        }
        return dest.getAbsolutePath();
    }

    public String upload(MultipartFile file) {
        InputStream inputStream = null;
        OutputStream os = null;
        String newFile = null;
        try {
            inputStream = file.getInputStream();
            String fileName = file.getOriginalFilename();
            // 2、保存到临时文件
            // 1K的数据缓冲
            byte[] bs = new byte[1024];
            // 读取到的数据长度
            int len;
            // 输出的文件流保存到本地文件
            File tempFile = new File(path);
            if (!tempFile.exists()) {
                tempFile.mkdirs();
            }
            newFile = tempFile.getPath() + File.separator + fileName;
            os = new FileOutputStream(newFile);
            // 开始读取
            while ((len = inputStream.read(bs)) != -1) {
                os.write(bs, 0, len);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 完毕，关闭所有链接
            try {
                os.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return newFile;
    }
}
