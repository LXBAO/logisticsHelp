package com.example.vo;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 *
 * </p>
 *
 * @author lx
 * @date 2024-03-22
 */
@Data

public class TeacherInfoVO implements Serializable {

  private static final long serialVersionUID = 1L;



  private Long id;

  /**
   * 用户id
   */
  private Integer userId;


  /**
   * 授课金额
   */
  private BigDecimal amt;

  /**
   * 授课金额
   */
  private BigDecimal amt2;

  /**
   * 授课金额
   */
  private BigDecimal amt3;

  /**
   * 授课金额
   */
  private BigDecimal amt4;

}
