package com.example.controller;

import com.example.common.SingleResponse;
import com.example.entity.OrderRemark;
import com.example.service.IOrderRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lx
 * @date 2024-04-29
 */
@RestController
@RequestMapping("/orderRemark")
public class OrderRemarkController {
@Autowired
private IOrderRemarkService orderRemarkService;
  @GetMapping("/find/by/{orderId}")
  public SingleResponse<OrderRemark> taking(@PathVariable Long orderId)   {

    return SingleResponse.buildSuccess( orderRemarkService.getInfoByOrderId(orderId));
  }
}
