package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author lx
 * date 2024/3/13 9:32
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Value("${file.dir}")
  private String fileDir;


  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**") // 对所有路径生效
        .allowedOrigins("*") // Vue应用的域名
        .allowedMethods("GET", "POST", "PUT", "DELETE") // 允许的请求方法
        .allowedHeaders("*") ;// 允许的请求头
         ; // 是否允许凭据
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    // 访问路径以 “” 开头时，会去 磁盘 fileDir  路径下找静态资源
    registry.addResourceHandler("/**")
        .addResourceLocations("file:" + fileDir);
  }

}

