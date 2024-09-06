package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author lx
 * @date 2024-04-29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@TableName("t_order_remark")
public class OrderRemark implements Serializable {

  private static final long serialVersionUID = 1L;
  private LocalDateTime createTime;
  private String description;
  @TableId(value = "id", type = IdType.ASSIGN_ID)
  private Long id;
  /**
   * 订单id
   */

  private Long orderId;
  /**
   * 类型1拒绝，2取消
   */
  private Integer types;
  private LocalDateTime updateTime;

}
