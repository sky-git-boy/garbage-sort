package com.sky.common.upload;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author sky
 * @create 2021-09-12 16:44
 */
@ConfigurationProperties(prefix = "upload")
@Data
public class UploadConfig {
    private String baseUrl;

    private List<String> allowTypes;
}
