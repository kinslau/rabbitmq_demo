package com.kinslau.demo.controller;





import com.kinslau.demo.service.BizService;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/test")
public class TestController  {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    @Autowired
    private BizService bizService;

    @GetMapping("send")
    @ResponseBody
    public Object send(@RequestParam(required = false,defaultValue = "send") String msg,
                       @RequestParam(required = false,defaultValue = "default") String type){

        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());



        if (type.equals("default")){
            this.rabbitTemplate.convertAndSend("defaultQueue",msg);
        }else if (type.equals("direct")){
            this.rabbitTemplate.convertAndSend("myDirectExchange","mine.direct",msg,correlationData);
        }else if (type.equals("fanout")){
            this.rabbitTemplate.convertAndSend("myFanoutExchange",null,msg,correlationData);
        }else if (type.equals("topic")){
            this.rabbitTemplate.convertAndSend("news-exchange","province.city.street.shop",msg,correlationData);
        }
        return type +" : "+ msg;
    }


    @GetMapping("biz")
    @ResponseBody
    public Object biz(@RequestParam(required = false,defaultValue = "send") String msg,
                       @RequestParam(required = false,defaultValue = "default") String type){

        this.bizService.submitOrder();
        return type +" : "+ msg;
    }


}
