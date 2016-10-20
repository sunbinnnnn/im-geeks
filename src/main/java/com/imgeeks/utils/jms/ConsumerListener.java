package com.imgeeks.utils.jms;

import org.apache.log4j.Logger;

/**
 * Created by tedo on 2016/9/27.
 */
public class ConsumerListener {
    private static final Logger logger = Logger.getLogger(ConsumerListener.class);
    public void handleMessage(String message) {
    }

    public String receiveMessage(String message) {
        logger.info("ConsumerListener通过receiveMessage接收到一个纯文本消息，消息内容是：" + message);
        return "这是ConsumerListener对象的receiveMessage方法的返回值。";
    }

}