package com.example.mq;

import com.example.entity.Message;
import com.example.service.IMessageService;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author lx
 * @data 2022/11/15 20:42
 */
/**
 * selectorExpression同步，异步，单项发送
 * messageModel 定义消费的模式  广播或者集群
 * consumeMode.ORDERLY消息循序,consumeMode.CONCURRENTLY
 */

@Component
@RocketMQMessageListener(consumerGroup = "anran-consumer-group", topic = "anran-topic",
        selectorExpression = "syn||asyn||way", messageModel = MessageModel.CLUSTERING,
        consumeMode = ConsumeMode.ORDERLY)
public class RocketConsumer implements RocketMQListener<Message> , RocketMQPushConsumerLifecycleListener {
    @Autowired
    private IMessageService messageService;
    @Override
    public void onMessage(Message message) {
        //根据消费类型 处理
        switch (message.getTypes()){
            case 1:
                messageService.send(message);
                break;
            case 2:
                //微信收
                break;
            case 3:
                //微信支
                break;
        }

    }

    @Override
    public void prepareStart(DefaultMQPushConsumer defaultMQPushConsumer) {
        //设置最大重试次数
        defaultMQPushConsumer.setMaxReconsumeTimes(6);

    }
}