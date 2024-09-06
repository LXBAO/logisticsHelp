package com.example.service;

import com.example.entity.Message;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lx
 * @date 2024-05-07
 */
public interface IMessageService extends IService<Message> {

   void saveMessage(Message message);

   void updateStatus(int status);
   void send(Message message);
   List<Message> getSendMessageList();
}
