package com.kinslau.demo.rabbitmq;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MqServiceImpl implements MqService {

    private static final Logger logger= LoggerFactory.getLogger(MqService.class);

    @Override
    public void saveMqMsg(String msgId, Object msg) {
        logger.info("MQ消息入库:{}",msgId);
        MqSchedule.map.put(msgId,msg);
    }


    @Override
    public void confirmMqMsg(String msgId) {
        logger.info("MQ消息处理成功:{}",msgId);
        MqSchedule.map.remove(msgId);
    }
}
