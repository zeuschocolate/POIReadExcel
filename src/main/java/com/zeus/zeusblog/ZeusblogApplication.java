package com.zeus.zeusblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(value = "com.zeus")
public class ZeusblogApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZeusblogApplication.class, args);
    }

}
