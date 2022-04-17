package com.uditagarwal.pub_sub_queue.impl;

import com.uditagarwal.pub_sub_queue.interfaces.Subscriber;
import com.uditagarwal.pub_sub_queue.interfaces.Topic;
import com.uditagarwal.pub_sub_queue.models.Message;
import lombok.Getter;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

@Getter
public class InMemoryTopic implements Topic {
    private final String topicName; // treating topic name as identifier
    private final List<Message> messages;
    private final List<Subscriber> subscribers;

    public InMemoryTopic(@NonNull final String topicName) {
        this.topicName = topicName;
        this.messages = new ArrayList<>();
        this.subscribers = new ArrayList<>();
    }

    public synchronized void addMessage(@NonNull final Message message) {
        messages.add(message);
    }

    public void addSubscriber(@NonNull final Subscriber subscriber) {
        subscribers.add(subscriber);
    }
}
