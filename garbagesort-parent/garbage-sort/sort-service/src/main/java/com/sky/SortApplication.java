package com.sky;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author sky
 * @create 2021-09-07 10:31
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.sky.mapper"})
@EnableDubbo
public class SortApplication {
    public static void main(String[] args) {
        SpringApplication.run(SortApplication.class, args);
    }
}
