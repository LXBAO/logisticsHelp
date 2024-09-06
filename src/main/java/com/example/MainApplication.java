package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableAsync
@EnableTransactionManagement
@EnableConfigurationProperties
@SpringBootApplication( )
@MapperScan(basePackages ="com.example.mapper")
public class MainApplication {

    public static void main(String[] args)  {
        try {
            SpringApplication.run(MainApplication.class, args);
        }catch (Exception e){
            System.out.println(e);
        }

    }
}

