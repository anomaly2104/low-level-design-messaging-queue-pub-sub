package com.uditagarwal.pub_sub_queue.interfaces;

import com.uditagarwal.pub_sub_queue.models.Message;

import java.util.concurrent.atomic.AtomicInteger;

public interface Subscriber {
    String getId();
    int getSleepTimeInMillis();
    AtomicInteger getOffset();
    void consume(Message message) throws InterruptedException;
}
