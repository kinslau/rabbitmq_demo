package com.kinslau.demo.rabbitmq;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class MqSchedule {

    private static final Logger logger = LoggerFactory.getLogger(MqSchedule.class);

    public static final ConcurrentHashMap<String,Object> map = new ConcurrentHashMap();

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private MqService mqService;

    @PostConstruct
    public void init(){
        this.rabbitTemplate.setConfirmCallback(confirmCallback);
    }

    @Scheduled(fixedRate = 1000 * 5,initialDelay = 1 * 60 * 1000)
    public void sendMsg(){

        map.forEach((k,msg)->{
            logger.info("消息服务定时发送消息,定时轮休待发送的消息");
            CorrelationData correlationData = new CorrelationData(k);
            this.rabbitTemplate.convertAndSend("news-exchange","province.city.street.shop",msg,correlationData);
        });

    }



    final RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
        @Override
        public void confirm(CorrelationData correlationData, boolean ack, String s) {
            System.out.println("回调消息:_ "+correlationData.toString());
            if (ack){
                MqSchedule.this.mqService.confirmMqMsg(correlationData.getId());
            }else {
                logger.info("发送失败:{}",correlationData.getId());
            }

        }
    };

}
