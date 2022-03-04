package com.sky;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author sky
 * @create 2021-09-08 8:32
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.sky.mapper"})
@EnableAsync
public class UniappApplication {
    public static void main(String[] args) {
        SpringApplication.run(UniappApplication.class, args);
    }
}
