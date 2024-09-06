package com.example.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.common.OrderStatus;
import com.example.entity.Order;
import com.example.param.OrderParam;
import com.example.vo.OrderRemarkVO;
import com.example.vo.OrderVO;
import com.github.yulichang.base.MPJBaseService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lx
 * @date 2024-03-22
 */
public interface IOrderService extends MPJBaseService<Order> {

  Long save(OrderVO orderVO);
  void cancelOrReject(OrderRemarkVO orderRemarkVO, OrderStatus newStatus) ;
  void updateStatus(Long id , Integer oldStatus,OrderStatus newStatus) ;

  IPage<Order> getOrderPage(OrderParam orderParam);
}
