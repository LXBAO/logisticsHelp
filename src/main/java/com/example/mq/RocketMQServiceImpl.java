package com.example.mq;

import com.alibaba.fastjson.JSONObject;

import com.example.entity.Message;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author lx
 * @data 2022/10/31 14:27
 */
@Service
public class RocketMQServiceImpl {
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Value(value = "${rocketmq.producer.topic}:${rocketmq.producer.sync-tag}")
    private String syncTag;

    @Value(value = "${rocketmq.producer.topic}:${rocketmq.producer.async-tag}")
    private String asyncTag;

    @Value(value = "${rocketmq.producer.topic}:${rocketmq.producer.oneway-tag}")
    private String oneWayTag;





    /**
     * 异步发送消息
     * @throws RuntimeException
     */

    public void asynMessage( )   {
        String id = UUID.randomUUID().toString();
/*
        Message<MessageVO> message = MessageBuilder.withPayload(messageVO)
                .setHeader(RocketMQHeaders.KEYS, id)
                .build();
        // 设置发送地和消息信息并发送异步消息
        rocketMQTemplate.asyncSend(asyncag, message, new SendCallbackListener(id));*/

    }

    /**
     * 延迟队列
     * @throws Exception
     */

    public void delayMessage()   {
        String id = UUID.randomUUID().toString();
        String messageStr = "order id : " + id;
        org.springframework.messaging.Message message = MessageBuilder.withPayload(messageStr)
                .setHeader(RocketMQHeaders.KEYS, id)
                .build();
        // 设置超时和延时推送
        // 超时时针对请求broker然后结果返回给product的耗时
        // 现在RocketMq并不支持任意时间的延时，需要设置几个固定的延时等级，从1s到2h分别对应着等级1到18
        // delayLevel = "1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h";
        SendResult sendResult = rocketMQTemplate.syncSend(syncTag, message, 6 * 1000L, 3);
        System.out.println("pushDelayMessage finish : " + id + ", sendResult : " + JSONObject.toJSONString(sendResult));

    }

    public void synMessage(Message messageVO)   {
        String id = UUID.randomUUID().toString();

        org.springframework.messaging.Message message = MessageBuilder.withPayload(messageVO)
            .setHeader(RocketMQHeaders.KEYS, id)
            .build();
        // 设置发送地和消息信息并发送同步消息
        SendResult sendResult = rocketMQTemplate.syncSend(syncTag, message);
        System.out.println("pushDelayMessage finish : " + id + ", sendResult : " + JSONObject.toJSONString(sendResult));


    }

    /**
     * 批量发送
     * @throws Exception
     */

    public void batchMessage(List<Message> list)   {

        String id = UUID.randomUUID().toString();
        List<org.springframework.messaging.Message> messages = new ArrayList<>();
        for (Message messageVO: list) {
            org.springframework.messaging.Message message = MessageBuilder.withPayload(messageVO)
                    .setHeader(RocketMQHeaders.KEYS, id)
                    .build();
            messages.add(message);
        }
        SendResult sendResult = rocketMQTemplate.syncSend(syncTag, messages);
        System.out.println("pushDelayMessage finish : " + id + ", sendResult : " + JSONObject.toJSONString(sendResult));
    }


    /***
     * 事务
     * @throws Exception
     */

    public void transactionMessage()   {
        String id = UUID.randomUUID().toString();
        String messageStr = "order id : " + id;
        org.springframework.messaging.Message message = MessageBuilder.withPayload(messageStr)
                .setHeader(RocketMQHeaders.KEYS, id)
                .setHeader("money", 10)
                .setHeader(RocketMQHeaders.TRANSACTION_ID, id)
                .build();
        TransactionSendResult transactionSendResult = rocketMQTemplate.sendMessageInTransaction(syncTag, message, null);
        System.out.println("pushTransactionMessage result : " + JSONObject.toJSONString(transactionSendResult));

    }
}
