package com.example.demo.mq;

import java.util.concurrent.CountDownLatch;

import com.example.demo.DemoApplication;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

//what is latch
//https://www.geeksforgeeks.org/countdownlatch-in-java/
@Component
public class Receiver {

    private static final Logger logger = LogManager.getLogger(DemoApplication.class);

    // Let us create task that is going to
    // wait for 1 thread before it starts
    private CountDownLatch latch = new CountDownLatch(1);

    public void receiveMessage(String message) {
       // System.out.println("Received <" + message + ">");
        logger.info("Received <" + message + ">");
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }

}
