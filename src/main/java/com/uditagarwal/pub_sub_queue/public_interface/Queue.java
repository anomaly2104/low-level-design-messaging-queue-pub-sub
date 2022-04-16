package com.uditagarwal.pub_sub_queue.public_interface;

import com.uditagarwal.pub_sub_queue.model.Message;

public interface Queue {
    void createTopic(String topicName);
    void addSubscriber(Subscriber subscriber, String topicName);
    void publishMessage(String topicName, Message message);
    void resetOffset(String topicName, Subscriber subscriber, Integer newOffset);
}
