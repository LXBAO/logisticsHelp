package com.example.service.impl;

import cn.hutool.core.map.MapUtil;

import com.alibaba.fastjson.JSONObject;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.Constant;
import com.example.common.OrderStatus;
import com.example.entity.Message;
import com.example.entity.Order;
import com.example.entity.User;
import com.example.mapper.OrderMapper;
import com.example.param.OrderParam;

import com.example.service.IMessageService;
import com.example.service.IOrderRemarkService;
import com.example.service.IOrderService;

import com.example.service.IUserService;
import com.example.vo.OrderRemarkVO;
import com.example.vo.OrderVO;

import com.example.vo.UserVO;

import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lx
 * @date 2024-03-22
 */
@Service
public class OrderServiceImpl extends MPJBaseServiceImpl<OrderMapper, Order> implements IOrderService {


  @Autowired
  private OrderMapper orderMapper;
  @Autowired
  private IUserService userService;

  @Autowired
  private IOrderRemarkService orderRemarkService;

  @Autowired
  private IMessageService messageService;

  @Override
  public Long save(OrderVO orderVO){
    Order order = new Order();
    BeanUtils.copyProperties(orderVO, order);
    order.setStatus(OrderStatus.NEW.getValue());
    orderMapper.insert(order);
    this.saveMessage(order,OrderStatus.NEW);
    return order.getId();
  }

  @Override
  public void cancelOrReject(OrderRemarkVO orderRemarkVO, OrderStatus newStatus) {
    updateStatus(orderRemarkVO.getOrderId(),orderRemarkVO.getOldStatus(),newStatus);
    orderRemarkService.save(orderRemarkVO);
  }


  /**
   * 根据id 修改状态
   */
  @Override
  public void updateStatus(Long id , Integer oldStatus,OrderStatus newStatus)   {

    Order order = orderMapper.selectById(id);
    if(order == null){
      throw new RuntimeException("没有此订单，请重新返回");
    }
    if(!Objects.equals(order.getStatus(), oldStatus)){
      throw new RuntimeException("订单已被修改，请重新刷新页面");
    }
    Order order2 = new Order();
    order2.setStatus(newStatus.getValue());
    UpdateWrapper<Order> updateWrapper = new UpdateWrapper<>();
    updateWrapper.eq("id",id);
    orderMapper.update(order2,updateWrapper);
    saveMessage(order,newStatus);

  }


  void saveMessage(Order order,OrderStatus status){
    Message message = new Message();
    message.setStatus(Constant.MSG_NEW);
    message.setTypes(Constant.MSG_TYPES_MESSAGE);
    message.setRetryTimes(3);
    String data = "";
    Map <String,Object> map = MapUtil.newHashMap();
    if(OrderStatus.NEW == status){
      UserVO userVO = userService.findUserById(order.getTeacherId());
      map.put("orderId",order.getId());
      map.put("desc","您有新的订单需要处理，请打开微信搜索易点通");
      map.put("phone",userVO.getPhone());
      data = JSONObject.toJSONString(map);
    }else if(OrderStatus.ACCEPT == status){
      UserVO userVO = userService.findUserById(order.getPatriarchId());
      map.put("orderId",order.getId());
      map.put("desc","您有新的订单需要处理，微信搜索易点通");
      map.put("phone",userVO.getPhone());
      data = JSONObject.toJSONString(map);
    }
    else if(OrderStatus.CANCEL == status  || OrderStatus.REJECT == status ){
      if(OrderStatus.CANCEL == status){
        UserVO userVO = userService.findUserById(order.getTeacherId());
        map.put("orderId",order.getId());
        map.put("desc","您的订单已被取消，微信搜索易点通查看取消原因");
        map.put("phone",userVO.getPhone());
        data = JSONObject.toJSONString(map);
      }else{
        UserVO userVO = userService.findUserById(order.getPatriarchId());
        map.put("orderId",order.getId());
        map.put("desc","您的订单已被拒绝，微信搜索易点通查看拒绝原因");
        map.put("phone",userVO.getPhone());
        data = JSONObject.toJSONString(map);
      }
    }
    message.setData(data);
    messageService.saveMessage(message);
  }


  @Override
  public IPage<Order> getOrderPage(OrderParam orderParam) {
    Page<Order> page = new Page<>(orderParam.getCurrPage(), orderParam.getPageSize());
    MPJLambdaWrapper<Order> wrapper = new MPJLambdaWrapper<Order>().selectAll(Order.class);

    if(orderParam.getPatriarchId() != null && orderParam.getPatriarchId() != 0){
      wrapper.select(User::getPhone,User::getUsername,User::getImageUrl,User::getAddressDetail,User::getSubjects).selectAs(User::getId,"userId")
          .leftJoin(User.class,User::getId,Order::getTeacherId);
      wrapper.eq(Order::getPatriarchId,orderParam.getPatriarchId());
    }

    if(orderParam.getTeacherId()!= null  && orderParam.getTeacherId() != 0){
      wrapper.select(User::getUsername,User::getAddressDetail,User::getPhone,User::getSex)
          .leftJoin(User.class,User::getId,Order::getPatriarchId);
      wrapper.eq(Order::getTeacherId,orderParam.getTeacherId());

    }
    wrapper.orderByDesc(Order::getCreateTime);


    return orderMapper.selectJoinPage(page, Order.class,wrapper);

  }

}
