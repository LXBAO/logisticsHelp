package com.example.param;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author lx
 * @data 2024/3/28 17:46
 */
@Data
public class UserParam {
  private Integer sex;
  private Integer currUserId;
  private String userName;
  private Integer tenantId;
  private BigDecimal startAmount;
  private BigDecimal endAmount;
  private int currPage;
  private Integer age;
  private int pageSize;
}
