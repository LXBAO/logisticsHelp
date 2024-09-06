
package com.example.config;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;


/**
 * @author lx
 * date 2024/4/24 15:17
 */

@Configuration
public class RedisConfig extends CachingConfigurerSupport {
  @Bean
  public RedisTemplate<Object,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
    RedisTemplate<Object,Object> redisTemplate=new RedisTemplate<>();
    // 默认的序列化器： new JdkSerializationRedisSerializer()
    redisTemplate.setKeySerializer(new StringRedisSerializer());
    redisTemplate.setHashKeySerializer(new JdkSerializationRedisSerializer());
    redisTemplate.setConnectionFactory(redisConnectionFactory);
    return redisTemplate;
  }
}

