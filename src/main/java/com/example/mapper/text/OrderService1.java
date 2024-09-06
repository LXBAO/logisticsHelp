package com.example.mapper.text;

import org.springframework.stereotype.Service;

/**
 * @author lx
 * @date 2024/7/4 11:32
 */
@Service
public class OrderService1  implements ProcessCallEvent{
  @Override
  public int shopping() {
    System.out.println("商品");
    return 0;
  }
}
