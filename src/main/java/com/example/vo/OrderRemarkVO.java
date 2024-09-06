package com.example.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lx
 * @data 2024/4/29 14:46
 */
@Data
public class OrderRemarkVO  implements Serializable {
  private static final long serialVersionUID = 1L;

  private String description;
  private Long id;

  private Long orderId;

  private int oldStatus;
  /**
   * 类型1拒绝，2取消
   */
  private Integer types;

}
