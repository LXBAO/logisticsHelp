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
@TableName("t_user")
public class User implements Serializable {

  private static final long serialVersionUID = 1L;
  /**
   * 地址
   */
  private String address;
  /**
   * 详细地址
   */
  private String addressDetail;

  private LocalDateTime createTime ;
  /**
   * 状态
   */
  private Integer flag;
  @TableId(value = "id", type = IdType.ASSIGN_ID)
  private Integer id;
  /**
   * 头像image
   */
  private String imageUrl;

  private String city;
  @TableField(exist = false)
  private BigDecimal amt;
  /**
   * 授课 金额
   */
  @TableField(exist = false)
  private BigDecimal amt2;

  @TableField(exist = false)
  private BigDecimal distance;
  /**
   * 授课 金额
   */
  @TableField(exist = false)
  private BigDecimal amt3;
  /**
   * 授课 金额
   */
  @TableField(exist = false)
  private BigDecimal amt4;
  /**
   * 维度
   */
  private BigDecimal latitude;
  /**
   * 经度
   */
  private BigDecimal longitude;
  /**
   * 微信id
   */
  private String openid;
  private Integer sex;
  /**
   * 性别
   * <p>
   *
   * <p>
   * /**
   * 电话
   */
  private String phone;
  /**
   * 教师是否被审核
   */
  private Integer process;
  /**
   * 擅长科目
   */
  private String subjects;
  /**
   * 租户1学生教师，2家长，3职业教师
   */
  private Integer tenantId;

  private LocalDateTime updateTime;
  /**
   * 姓名
   */
  private String username;
  private BigDecimal assess; //综合评分

}
