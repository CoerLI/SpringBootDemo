package com.lihang.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
//指定扫描包扫描mapper
@MapperScan("com.lihang.springboot.mapper")
public class SpringWebServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringWebServerApplication.class, args);
    }
}
