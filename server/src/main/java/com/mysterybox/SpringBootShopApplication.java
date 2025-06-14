package com.mysterybox;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.mysterybox.mapper")
public class SpringBootShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootShopApplication.class, args);
    }

}
