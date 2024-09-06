package com.example.common;


import java.io.Serializable;

/**
 * @author lx
 * @date 2024/3/22 14:34
 */
public class Response implements Serializable {
  private static final long serialVersionUID = 1L;

  private String code;

  private String message;

  /**
   * @param errCode
   * @param errMessage
   * @return
   */
  public static Response buildFailure(String errCode, String errMessage) {
    Response response = new Response();
    response.setCode(errCode);
    response.setMessage(errMessage);
    return response;
  }

  /**
   * @title 成功消息
   * @return
   */
  public static Response buildSuccess() {
    Response response = new Response();
    response.setSuccess();
    return response;
  }

  public boolean isSuccess() {
    return ResponseCode.SUCCESS.code.equals(this.code);
  }

  protected void setSuccess() {
    this.code = ResponseCode.SUCCESS.code;
  }


  public Response() {
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public String toString() {
    return "Response{" +
        "code='" + code + '\'' +
        ", message='" + message + '\'' +
        '}';
  }


}
