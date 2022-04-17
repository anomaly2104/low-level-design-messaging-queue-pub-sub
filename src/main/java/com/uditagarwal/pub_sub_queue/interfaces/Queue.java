package com.uditagarwal.pub_sub_queue.interfaces;

public interface Queue {
    void createTopic(String topicName);
    void addSubscriber(String subscriberId, String topicName, int sleepTimeInMs);
    void publishMessage(String topicName, String message);
    void resetOffset(String topicName, String subscriberId, Integer newOffset);
}
