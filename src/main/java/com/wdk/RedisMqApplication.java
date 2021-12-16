package com.wdk;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: pengmin
 * @date: 2021/12/16 11:18
 */
@SpringBootApplication
@MapperScan({"com.wdk.redis.mapper"})
public class RedisMqApplication {
    public static void main(String[] args) {
        SpringApplication.run(RedisMqApplication.class, args);
    }
}
