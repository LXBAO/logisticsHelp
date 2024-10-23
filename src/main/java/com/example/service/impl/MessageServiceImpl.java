package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.common.Constant;
import com.example.entity.Message;

import com.example.mapper.MessageMapper;

import com.example.service.IMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lx
 * @date 2024-05-07
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements IMessageService {

  @Autowired
  private MessageMapper messageMapper;


  @Override
  public void saveMessage(Message message) {
      messageMapper.insert(message);
  }

  @Override
  public void send(Message message) {
    //发送失败修状态
    UpdateWrapper<Message> updateWrapper = new UpdateWrapper<>();
    updateWrapper.eq("id", message.getId());
    Message newMessage = new Message();
    if(Objects.equals(message.getStatus(), Constant.MSG_FAIL)){
      newMessage.setRetryTimes(message.getRetryTimes()-1); //重试次数 减1
    }

    if(true){
      newMessage.setStatus(Constant.MSG_SUCCESS);
    }else {
      newMessage.setStatus(Constant.MSG_FAIL);
    }

    messageMapper.update(newMessage,updateWrapper);
    //发送成功修改状态
  }

  /**
   * 定时获取数据库消息
   */
  @Bean
  public TimerTask timerTask() {
    TimerTask timerTask = new TimerTask() {
      @Override
      public void run() {
        List<Message> list = getSendMessageList();
        if(CollectionUtils.isNotEmpty(list)){
            updateStatus(Constant.MSG_SENDING);
            //rocketMQServiceImpl.batchMessage( list);
        }
      }
    };
    Timer timer = new Timer();
    //这里是每60秒执行一次
    timer.schedule(timerTask,10,60000);
    return timerTask;
  }

  /**
   * 获取需要处理的消息  (新建，处理失败的)
   * @return
   */
  @Override
  public List<Message> getSendMessageList() {
    //当前天
    LocalDateTime currentDate = LocalDateTime.now();
    QueryWrapper<Message> wrapper = new QueryWrapper<>();
    //获取前一天
    LocalDateTime preDate = currentDate.minusDays(1);

    wrapper.select("id","data","types","status","retry_times");

    wrapper.between("create_time",preDate,currentDate);

    wrapper.gt("retry_times", 0);
    wrapper.and(exportWrapper->exportWrapper.eq("status", Constant.MSG_NEW).or().eq("status", Constant.MSG_FAIL
    ));


    return messageMapper.selectList(wrapper);
  }

  /**
   * 修改订单状态进行中
   */
  public void updateStatus(int status){
    //当前天
    LocalDateTime currentDate = LocalDateTime.now();
    UpdateWrapper<Message> updateWrapper = new UpdateWrapper<>();
    //获取前一天
    LocalDateTime preDate = currentDate.minusDays(1);

    updateWrapper.between("create_time",preDate,currentDate);
    updateWrapper.and(exportWrapper->exportWrapper.eq("status", Constant.MSG_NEW).or().eq("status", Constant.MSG_FAIL
    ));

    Message message = new Message();
    message.setStatus(status);
    messageMapper.update(message,updateWrapper);
  }

}
