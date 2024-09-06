package com.example.service;

import com.example.entity.OrderRemark;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.vo.OrderRemarkVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lx
 * @date 2024-04-29
 */
public interface IOrderRemarkService extends IService<OrderRemark> {

  void save(OrderRemarkVO orderRemarkVO);

   OrderRemark  getInfoByOrderId(Long orderId);
}
