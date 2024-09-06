package com.example.mapper.text;

import org.springframework.stereotype.Service;

/**
 * @author lx
 * @date 2024/7/4 11:33
 */
@Service
public class UserService1 implements  ProcessCallEvent {
  @Override
  public int shopping() {
    System.out.println("用户购物");
    return 0;
  }
}
