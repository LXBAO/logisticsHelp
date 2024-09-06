package com.example.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author lx
 * @data 2024/4/1 10:02
 * 1已下单，2已接单，3已支付，4取消,5已拒绝，6已完成 payment
 */
@AllArgsConstructor
@Getter
public enum OrderStatus {
  NEW(1, "已下单"),
  ACCEPT(2, "已接单"),
  PAYMENT(3, "已支付"),
  CANCEL(4, "已取消"),
  REJECT(5, "已拒绝"),
  COMPLETED (6, "已完成");

  private    int  value;
  private    String name;

  private static final Map<Integer, OrderStatus>  ORDER_STATUS_MAP = Collections
      .unmodifiableMap(Arrays.stream(OrderStatus.values()).collect(Collectors.toMap(OrderStatus::getValue, e -> e)));

  OrderStatus() {
  }

  public static String getName(String value) {
    if (value != null) {
      OrderStatus enums = ORDER_STATUS_MAP.get(value);
      if (enums == null) {
        return "";
      } else {
        return enums.getName();
      }
    }
    return "";
  }
}
