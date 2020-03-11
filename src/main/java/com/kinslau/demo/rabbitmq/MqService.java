package com.kinslau.demo.rabbitmq;
import org.springframework.stereotype.Service;

/**
 * 基于本地消息的最终一致性
 */
public interface MqService {



    void saveMqMsg(String msgId,Object msg);


    void confirmMqMsg(String msgId);


}
