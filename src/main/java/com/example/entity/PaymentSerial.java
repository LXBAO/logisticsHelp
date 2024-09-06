package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 *
 * </p>
 *
 * @author lx
 * @date 2024-05-06
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@TableName("t_payment_serial")
public class PaymentSerial implements Serializable {

  private static final long serialVersionUID = 1L;
  /**
   * 金额
   */
  private BigDecimal amt;
  @TableId(value = "id", type = IdType.ASSIGN_ID)
  private Long id;
  /**
   * 支付流水号
   */
  private String numNo;
  /**
   * 用户唯一id
   */
  private String openId;
  private Long orderId;
  /**
   * 备注
   */
  private String remark;
  /**
   * 用户名
   */
  private String username;

}
