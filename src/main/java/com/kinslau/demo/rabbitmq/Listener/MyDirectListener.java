package com.kinslau.demo.rabbitmq.Listener;


import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RabbitListener(bindings = @QueueBinding(
        value = @Queue("myDirectQueue"),
        exchange = @Exchange(value = "myDirectExchange"),
        key = "mine.direct"
))
public class MyDirectListener {

    @RabbitHandler
    public void onMessage(@Payload String msg, @Headers Map<String, Object> headers) {
        System.out.println("来自" + headers.get(AmqpHeaders.CONSUMER_QUEUE) + "的消息:" + msg);
    }
}