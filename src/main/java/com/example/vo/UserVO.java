package com.example.vo;

import com.example.entity.Assess;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author lx
 * @date 2024-03-22
 */
@Data
public class UserVO   implements  Serializable {

  private static final long serialVersionUID = 1L;
  /**
   * 地址
   */
  private String address;
  /**
   * 详细地址
   */
  private String addressDetail;

  private LocalDateTime createTime;

  /**
   * 状态
   */
  private Integer flag;
  private Integer id;
  /**
   * 头像image
   */
  private String imageUrl;
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
  /**
   * 电话
   */
  private String phone;
  /**
   * 教师是否被审核
   */
  private Integer process;
  /**
   * 性别
   */
  private Integer sex;

  private String city;
  /**
   * 擅长科目
   */
  private String subjects;
  /**
   * 租户1学生教师，2家长，3职业教师
   */
  private Integer tenantId;

  private LocalDateTime updateTime ;
  /**
   * 姓名
   */
  private String username;
  private BigDecimal assess; //综合评分
  private List<AttachmentVO> attachmentVOList;
  //评价表
  private List<Assess> assessList;
  //可授年纪和金额
  private  TeacherInfoVO  teacherInfoVO;

}
