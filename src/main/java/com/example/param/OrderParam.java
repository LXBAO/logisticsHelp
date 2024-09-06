package com.example.param;

import lombok.Data;


/**
 * @author lx
 * @data 2024/3/28 17:46
 */
@Data
public class OrderParam {
  private Integer patriarchId;
  private Integer teacherId;

  private int currPage;

  private int pageSize;
}
