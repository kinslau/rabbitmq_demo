package com.kinslau.demo.config.rabbit;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfig {

    @Autowired
    private CachingConnectionFactory connectionFactory;





    @Bean
    public RabbitTemplate rabbitTemplate(){
       RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
       connectionFactory.setPublisherConfirmType(CachingConnectionFactory.ConfirmType.CORRELATED);
       return rabbitTemplate;
    }
}
