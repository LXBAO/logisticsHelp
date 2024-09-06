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
 * @date 2024-03-22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@TableName("t_assess")
public class Assess implements Serializable {

  private static final long serialVersionUID = 1L;

  private LocalDateTime createTime ;
  /**
   * 描述
   */

  private String description;
  /**
   * 评分
   */

  private Integer fraction;

  @TableId(value = "id", type = IdType.ASSIGN_ID)
  private Long id;
  /**
   * 老师id
   */

  private Integer teacherId;

  private LocalDateTime updateTime;

}
