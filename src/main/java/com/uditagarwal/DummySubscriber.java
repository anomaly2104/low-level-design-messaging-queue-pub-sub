package com.uditagarwal;

import com.uditagarwal.pub_sub_queue.public_interface.ISubscriber;
import com.uditagarwal.pub_sub_queue.model.Message;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DummySubscriber implements ISubscriber {
    private final String id;
    private final int sleepTimeInMillis;

    public DummySubscriber(String id, int sleepTimeInMillis) {
        this.id = id;
        this.sleepTimeInMillis = sleepTimeInMillis;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void consume(Message message) throws InterruptedException {
        log.info("Subscriber: " + id + " started consuming: " + message.getMsg());
        Thread.sleep(sleepTimeInMillis);
        log.info("Subscriber: " + id + " done consuming: " + message.getMsg());
    }
}
