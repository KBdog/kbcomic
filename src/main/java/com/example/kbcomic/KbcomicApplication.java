package com.example.kbcomic;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan(value = "com.example.kbcomic.mapper")
@ComponentScan(basePackages = {"com.example.kbcomic.*"})
public class KbcomicApplication {
    public static void main(String[] args) {
        SpringApplication.run(KbcomicApplication.class, args);
    }
}
