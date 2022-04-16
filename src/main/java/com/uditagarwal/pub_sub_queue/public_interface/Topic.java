package com.uditagarwal.pub_sub_queue.public_interface;

import com.uditagarwal.pub_sub_queue.model.Message;
import com.uditagarwal.pub_sub_queue.model.TopicSubscriber;

import java.util.List;

public interface Topic {
    void addMessage(Message message);
    void addSubscriber(TopicSubscriber subscriber);
    List<TopicSubscriber> getSubscribers();
    List<Message> getMessages();
}
