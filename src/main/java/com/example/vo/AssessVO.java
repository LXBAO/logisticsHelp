package com.example.vo;

import lombok.Data;

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
public class AssessVO implements Serializable {

  private static final long serialVersionUID = 1L;
  private LocalDateTime createTime;
  /**
   * 描述
   */
  private String description;
  /**
   * 评分
   */
  private Integer fraction;
  private Long id;

  private Long orderId;
  /**
   * 老师id
   */
  private Integer teacherId;
  private LocalDateTime updateTime ;

}
