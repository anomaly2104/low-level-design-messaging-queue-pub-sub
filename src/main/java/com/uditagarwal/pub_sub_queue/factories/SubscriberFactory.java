package com.uditagarwal.pub_sub_queue.factories;

import com.uditagarwal.pub_sub_queue.model.TopicSubscriber;
import com.uditagarwal.pub_sub_queue.public_interface.ISubscriber;

public class SubscriberFactory {

    public static TopicSubscriber getTopicSubscriber(ISubscriber subscriber) {
        return new TopicSubscriber(subscriber);
    }
}
