package com.example.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.common.OrderStatus;
import com.example.common.SingleResponse;
import com.example.entity.Order;
import com.example.param.OrderParam;
import com.example.service.IOrderService;
import com.example.vo.OrderRemarkVO;
import com.example.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lx
 * @date 2024-03-22
 */
@RestController
@RequestMapping("/order")
public class OrderController {

  @Autowired
  private IOrderService orderService;

  @PostMapping("/save")
  public SingleResponse save(@RequestBody @Validated OrderVO orderVO) {

    return SingleResponse.buildSuccess(orderService.save(orderVO));
  }
  

  @PostMapping("/cancel")
  public SingleResponse<Integer> cancel(@RequestBody @Validated OrderRemarkVO orderRemarkVO)  {
    orderService.cancelOrReject(orderRemarkVO,OrderStatus.CANCEL);
    return SingleResponse.buildSuccess();
  }


  @PostMapping("/reject")
  public SingleResponse<Integer> reject(@RequestBody @Validated OrderRemarkVO orderRemarkVO)   {
    orderService.cancelOrReject(orderRemarkVO,OrderStatus.REJECT);
    return SingleResponse.buildSuccess();
  }

  /**
   * 接单
   * @param id
   * @return
   * @throws Exception
   */
  @GetMapping("/taking/{id}")
  public SingleResponse<Integer> taking(@PathVariable Long id)   {
    orderService.updateStatus(id, OrderStatus.NEW.getValue(), OrderStatus.ACCEPT);
    return SingleResponse.buildSuccess();
  }

  @PostMapping("/page")
  public IPage<Order> page(@RequestBody OrderParam orderParam) {

    return orderService.getOrderPage(orderParam);
  }
}

