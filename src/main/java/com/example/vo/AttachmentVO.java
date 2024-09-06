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

public class AttachmentVO implements Serializable {



  private LocalDateTime createTime;
  /**
   * 是否有效
   */

  private Integer flag;

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



  private LocalDateTime updateTime;
  /**
   * 附件地址
   */

  private String url;

  private String path;

}
