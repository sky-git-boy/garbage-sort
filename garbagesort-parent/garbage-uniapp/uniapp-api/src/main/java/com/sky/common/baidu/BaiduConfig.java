package com.sky.common.baidu;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author sky
 * @create 2021-09-12 19:45
 */
@ConfigurationProperties(prefix = "baidu")
@Data
public class BaiduConfig {

    private String appid;

    private String apikey;

    private String secretkey;

}
