package com.kinslau.demo.service;


import com.kinslau.demo.rabbitmq.MqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class BizServiceImpl implements BizService {

    @Autowired
    private MqService mqService;




    @Transactional
    @Override
    public void submitOrder() {
        // 消息入库
        mqService.saveMqMsg(UUID.randomUUID().toString(),"null");
        // 业务操作
        this.operate();
    }



    public void operate(){
        System.out.println("进行业务操作");

    }
}
