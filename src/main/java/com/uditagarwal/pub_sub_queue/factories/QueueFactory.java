package com.uditagarwal.pub_sub_queue.factories;

import com.uditagarwal.pub_sub_queue.impl.InMemoryQueue;
import com.uditagarwal.pub_sub_queue.interfaces.Queue;

public class QueueFactory {
    public static Queue getInMemoryQueue() {
        return new InMemoryQueue();
    }
}
