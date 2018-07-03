package com.example.demo;

import com.example.demo.entity.Item;
import com.example.demo.mq.QueueService;
import com.example.demo.mq.QueueServiceImp;
import com.example.demo.mq.Receiver;
import com.example.demo.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class Init implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    ItemService itemService;

    @Autowired
    QueueServiceImp queueServiceImp;

    @Autowired
    private Receiver receiver;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        Item item1 = new Item("item1", "this is item1");
        itemService.create(item1);
        Item item2 = new Item("item2", "this is item2");
        itemService.create(item2);

        queueServiceImp.sendHello();

        //https://www.geeksforgeeks.org/countdownlatch-in-java/
        //The main task waits for
        try {
            receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
