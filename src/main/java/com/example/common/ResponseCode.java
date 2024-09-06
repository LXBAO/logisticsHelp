package com.example.common;

/**
 * @author lx
 * @data 2024/3/22 14:41
 */
public enum ResponseCode {
  SUCCESS("200");
  public final String code;

  private ResponseCode(String code) {
    this.code = code;
  }

}
