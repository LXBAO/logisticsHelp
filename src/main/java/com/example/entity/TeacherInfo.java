package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author lx
 * @date 2024-04-08
 */
@Getter
@Setter
@TableName("t_teacher_info")
public class TeacherInfo implements Serializable {

  private static final long serialVersionUID = 1L;
  /**
   * 授课 金额
   */
  private BigDecimal amt;
  /**
   * 授课 金额
   */
  private BigDecimal amt2;
  /**
   * 授课 金额
   */
  private BigDecimal amt3;
  /**
   * 授课 金额
   */
  private BigDecimal amt4;
  private LocalDateTime createTime ;
  @TableId(value = "id", type = IdType.ASSIGN_ID)
  private Long id;
  private LocalDateTime updateTime;
  /**
   * 用户id
   */
  private Integer userId;

}
