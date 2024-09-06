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
@TableName("t_attachment")
public class Attachment implements Serializable {

  private static final long serialVersionUID = 1L;



  private LocalDateTime createTime   ;
  /**
   * 是否有效
   */

  private Integer flag;
  @TableId(value = "id", type = IdType.ASSIGN_ID)
  private Long id;
  /**
   * 父表id,订单id或者userid
   */

  private Integer parentId;
  /**
   * 1 用户，2 订单
   */
  private Integer type;
  /**
   * 后缀
   */

  private String suffix;



  private LocalDateTime updateTime ;
  /**
   * 附件地址
   */

  private String url;

  private String path;

}
