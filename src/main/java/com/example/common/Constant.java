package com.example.common;

/**
 * @author lx
 * @data 2024/3/23 9:54
 */
public class Constant {
  //附件表type 1是用户，2是订单
  public final static Integer ATTACHMENT_TYPE_USER_ID = 1;

  public final static Integer ATTACHMENT_TYPE_ORDER_ID = 2;




  public final static Integer MSG_NEW = 1; //消息新建

  public final static Integer MSG_SENDING = 2;//发送中

  public final static Integer MSG_FAIL = 3;//发送失败

  public final static Integer MSG_SUCCESS = 4;//发送成功
  public final static Integer MSG_TYPES_MESSAGE = 1;//短信消息
  public final static Integer MSG_TYPES_WEIXIN_COLLECT = 2;//微信支付接口收
  public final static Integer MSG_TYPES_WEIXIN_PAY = 3;//发送成功

}
