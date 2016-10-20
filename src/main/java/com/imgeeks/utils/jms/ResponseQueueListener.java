package com.imgeeks.utils.jms;

import org.apache.log4j.Logger;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Created by tedo on 2016/9/27.
 */
public class ResponseQueueListener implements MessageListener {
    private static final Logger logger = Logger.getLogger(ResponseQueueListener.class);

    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) message;
            try {
                logger.info("接收到发送到responseQueue的一个文本消息，内容是：" + textMessage.getText());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}