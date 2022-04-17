package com.uditagarwal.pub_sub_queue.interfaces;

import com.uditagarwal.pub_sub_queue.models.Message;

import java.util.List;

public interface Topic {
    void addMessage(Message message);
    void addSubscriber(Subscriber subscriber);
    List<Subscriber> getSubscribers();
    List<Message> getMessages();
}
