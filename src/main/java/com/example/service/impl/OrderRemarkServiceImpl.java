package com.example.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.OrderRemark;
import com.example.mapper.OrderRemarkMapper;
import com.example.service.IOrderRemarkService;
import com.example.vo.OrderRemarkVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lx
 * @date 2024-04-29
 */
@Service
public class OrderRemarkServiceImpl extends ServiceImpl<OrderRemarkMapper, OrderRemark> implements IOrderRemarkService {

  @Autowired
  private OrderRemarkMapper orderRemarkMapper;
  @Override
  public void save(OrderRemarkVO orderRemarkVO) {
    OrderRemark orderRemark = new OrderRemark();
    BeanUtils.copyProperties(orderRemarkVO,orderRemark);
    orderRemarkMapper.insert(orderRemark);
  }

  @Override
  public  OrderRemark  getInfoByOrderId(Long orderId) {
    QueryWrapper<OrderRemark> queryWrapper =new QueryWrapper();
    queryWrapper.eq("order_id",orderId);
    return orderRemarkMapper.selectOne(queryWrapper);
  }
}
