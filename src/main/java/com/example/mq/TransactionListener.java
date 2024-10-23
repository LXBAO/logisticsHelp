package com.example.mq;


import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.messaging.Message;

import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


/**
 * @author lx
 * @data 2022/11/16 11:30
 */
@RocketMQTransactionListener
@Component
public class TransactionListener implements RocketMQLocalTransactionListener {
    private static final ConcurrentMap<String, RocketMQLocalTransactionState> TRANSACTION_STATE_MAP = new ConcurrentHashMap<>();

    /**
     * 处理本地事务
     * @param message
     * @param o
     * @return
     */
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object o) {

        System.out.println("TX message listener execute local transaction");
        RocketMQLocalTransactionState result;
        try {
            // 业务代码（ 例如下订单 ）
            result = RocketMQLocalTransactionState.COMMIT;
        } catch (RuntimeException e) {
            System.out.println("execute local transaction error");
            result = RocketMQLocalTransactionState.UNKNOWN;
        }
        return result;


    }

    /**
     * 校验事务状态
     * @param message
     * @return
     */
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
        System.out.println("TX message listener check local transaction");
        RocketMQLocalTransactionState result;
        try {
            //业务代码（ 根据检查结果，决定是COMMIT或ROLLBACK ）
            result = RocketMQLocalTransactionState.COMMIT;
        } catch (RuntimeException e) {
            // 异常就回滚
            System.out.println("check local transaction error");
            result = RocketMQLocalTransactionState.ROLLBACK;
        }
        return result;


    }

}
