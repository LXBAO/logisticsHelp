package com.example.vo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.Data;

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
public class OrderVO implements Serializable {

  private static final long serialVersionUID = 1L;
  /**
   * 交易金额
   */
  private BigDecimal amount;
  private LocalDateTime createTime  ;
  private Long id;
  /**
   * 家长id
   */
  private Integer patriarchId;
  /**
   * 0未开始，1已开始，2未支付，4已完成
   */
  private Integer status;

  private String remark;

  private String userName;

  private String address;

  private String addressDetail;

  private String imageUrl;


  private String userId;



  private String phone;

  /**
   * 教师id
   */
  private Integer teacherId;
  private LocalDateTime updateTime ;
  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  private LocalDateTime startTime;
  private String ageStr;
  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  private LocalDateTime endTime;


}
