package com.uditagarwal.pub_sub_queue.public_interface;

import com.uditagarwal.pub_sub_queue.model.Message;

public interface Subscriber {
    String getId();
    void consume(Message message) throws InterruptedException;
}
