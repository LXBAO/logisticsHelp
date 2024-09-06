package com.example.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lx
 * date 2024/3/23 9:32
 */
@Configuration
public class MyBatisPlusConfig {
  @Bean
  public MybatisPlusInterceptor mybatisPlusInterceptor(){

    // 初始化 MybatisPlusInterceptor 核心插件
    MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
    // 添加自动分页插件 PaginationInnerInterceptor
    interceptor.addInnerInterceptor(new PaginationInnerInterceptor());
    // 返回
    return interceptor;
  }

}