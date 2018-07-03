package com.example.demo.mq;

import com.example.demo.DemoApplication;
import com.example.demo.config.RabbitMQConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class QueueServiceImp implements QueueService{

    private static final Logger logger = LogManager.getLogger(DemoApplication.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void sendHello() {
        logger.info("Send Hello");
        rabbitTemplate.convertAndSend(RabbitMQConfig.topicExchangeName, "foo.bar.baz", "Hello from RabbitMQ!");
    }

    @Override
    public void sendMessage(String msg) {
        logger.info("Send msg:"+msg);
        rabbitTemplate.convertAndSend(RabbitMQConfig.topicExchangeName, "foo.bar.baz", msg);
    }
}
