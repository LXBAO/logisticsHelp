package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.*;

/**
 * <p>
 * 
 * </p>
 *
 * @author lx
 * @date 2024-05-07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@TableName("t_message")
public class Message implements Serializable {

      private static final long serialVersionUID = 1L;

      private Long id;
      private Integer types; //1 短信消息，2微信支付 收，3 微信支付 付
      private LocalDateTime createTime;
      private LocalDateTime updateTime;
      private String data;
      private Integer retryTimes; //重试剩余次数
      private Integer status;  // 1消息新建 2发送中 3发送失败 4发送成功


}
