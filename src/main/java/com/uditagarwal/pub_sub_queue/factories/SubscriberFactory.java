package com.uditagarwal.pub_sub_queue.factories;

import com.uditagarwal.pub_sub_queue.impl.DummySubscriber;
import com.uditagarwal.pub_sub_queue.interfaces.Subscriber;

public class SubscriberFactory {

    public static Subscriber getDummySubscriber(String subscriberId, int sleepTimeInMs) {
        return new DummySubscriber(subscriberId, sleepTimeInMs);
    }
}
