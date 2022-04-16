package com.uditagarwal.pub_sub_queue.public_interface;

import com.uditagarwal.pub_sub_queue.model.Message;
import com.uditagarwal.pub_sub_queue.model.Topic;

public interface IQueue {

    void createTopic(String topicName);

    void addSubscriber(ISubscriber subscriber, String topicName);

    void publishMessage(String topicName, Message message);

    void resetOffset(String topicName, ISubscriber subscriber, Integer newOffset);
}
