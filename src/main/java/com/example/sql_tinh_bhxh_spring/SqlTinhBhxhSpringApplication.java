package com.example.sql_tinh_bhxh_spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class SqlTinhBhxhSpringApplication {
    public static void main(String[] args) {
        SpringApplication.run(SqlTinhBhxhSpringApplication.class, args);
    }
}
