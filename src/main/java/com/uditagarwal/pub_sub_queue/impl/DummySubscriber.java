package com.uditagarwal.pub_sub_queue.impl;

import com.uditagarwal.pub_sub_queue.interfaces.Subscriber;
import com.uditagarwal.pub_sub_queue.models.Message;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Getter
public class DummySubscriber implements Subscriber {
    private final String id;
    private final int sleepTimeInMillis;
    private final AtomicInteger offset;

    public DummySubscriber(String id, int sleepTimeInMillis) {
        this.id = id;
        this.sleepTimeInMillis = sleepTimeInMillis;
        this.offset = new AtomicInteger(0);
    }

    @Override
    public void consume(Message message) throws InterruptedException {
        log.info("Subscriber: " + id + " started consuming: " + message.getMsg());
        Thread.sleep(sleepTimeInMillis);
        log.info("Subscriber: " + id + " done consuming: " + message.getMsg());
    }
}
