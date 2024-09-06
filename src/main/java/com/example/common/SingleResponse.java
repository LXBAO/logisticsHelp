package com.example.common;

import lombok.Getter;

/**
 * @author lx
 * @data 2024/3/22 14:43
 */
@Getter
public class SingleResponse<T> extends Response {

  private T data;

  public static SingleResponse buildFailure(String errCode, String errMessage) {
    SingleResponse response = new SingleResponse();
    response.setCode(errCode);
    response.setMessage(errMessage);
    return response;
  }

  public static SingleResponse buildSuccess() {
    return buildSuccess((Object) null);
  }

  public static <T> SingleResponse<T> buildSuccess(T data) {
    SingleResponse response = new SingleResponse();
    response.setSuccess();
    response.setData(data);
    return response;
  }

    public void setData(T data) {
    this.data = data;
  }

}
