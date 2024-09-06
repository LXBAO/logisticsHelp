package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author lx
 * @date 2024-03-22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@TableName("t_order")
public class Order implements Serializable {

  private static final long serialVersionUID = 1L;
  /**
   * 交易金额
   */

  private BigDecimal amount;

  private LocalDateTime createTime ;
  @TableId(value = "id", type = IdType.ASSIGN_ID)
  private Long id;
  /**
   * 家长id
   */

  private String remark;
  private Integer patriarchId;
  /**
   *1已下单，2已接单，3支付，4取消,5已拒绝，6已完成
   */

  private Integer status;

  @TableField(exist = false)
  private String userName;
  @TableField(exist = false)
  private String address;
  @TableField(exist = false)
  private String addressDetail;
  @TableField(exist = false)
  private String imageUrl;

  @TableField(exist = false)
  private String userId;

  @TableField(exist = false)
  private String subjects;

  @TableField(exist = false)
  private String phone;

  @TableField(exist = false)
  private int sex;



  /**
   * 教师id
   */

  private Integer teacherId;

  private LocalDateTime updateTime ;

  private LocalDateTime startTime;

  private LocalDateTime endTime;

  private String ageStr;

}
