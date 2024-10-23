package com.example.mq;

import com.alibaba.fastjson.JSONObject;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;

/**
 * @author lx
 * @data 2022/11/16 10:02
 */
    public class SendCallbackListener implements SendCallback {

        private String id;

        public SendCallbackListener(String id) {
            this.id = id;
        }

        @Override
        public void onSuccess(SendResult sendResult) {
            System.out.println("CallBackListener on success : " + JSONObject.toJSONString(sendResult));
        }

        @Override
        public void onException(Throwable throwable) {
            System.out.println("CallBackListener on exception : "+ throwable);
        }

}
