package com.kinslau.demo.rabbitmq.Listener;


import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RabbitListener(queuesToDeclare = @Queue("defaultQueue"))
public class MyDefaultListener  {

    @RabbitHandler
    public void onMessage(@Payload String msg, @Headers Map<String, Object> headers) {
        System.out.println("来自" + headers.get(AmqpHeaders.CONSUMER_QUEUE) + "的消息:" + msg);

    }


}
