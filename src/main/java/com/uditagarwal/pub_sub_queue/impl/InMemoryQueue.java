package com.uditagarwal.pub_sub_queue.impl;

import com.uditagarwal.pub_sub_queue.factories.MessageFactory;
import com.uditagarwal.pub_sub_queue.factories.SubscriberFactory;
import com.uditagarwal.pub_sub_queue.factories.TopicFactory;
import com.uditagarwal.pub_sub_queue.handler.TopicProcessor;
import com.uditagarwal.pub_sub_queue.interfaces.Queue;
import com.uditagarwal.pub_sub_queue.interfaces.Subscriber;
import com.uditagarwal.pub_sub_queue.interfaces.Topic;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class InMemoryQueue implements Queue {

    private final Map<String, TopicProcessor> topicNameToProcessorMap;

    public InMemoryQueue() {
        this.topicNameToProcessorMap = new HashMap<>();
    }

    public void createTopic(@NonNull final String topicName) {
        final Topic topic = TopicFactory.getNewTopic(topicName);
        final TopicProcessor topicProcessor = TopicFactory.getNewTopicProcessor(topic);
        topicNameToProcessorMap.put(topicName, topicProcessor);
        log.info("created topic with name: {}", topicName);
    }

    public void addSubscriber(@NonNull final String subscriberId,
                              @NonNull final String topicName,
                              final int sleepTimeInMs) {
        Subscriber subscriber = SubscriberFactory.getDummySubscriber(subscriberId, sleepTimeInMs);
        getTopic(topicName).addSubscriber(subscriber);
        log.info(subscriber.getId() + " subscribed to topic: " + topicName);
    }

    private Topic getTopic(String topicName) {
        return topicNameToProcessorMap.get(topicName).getTopic();
    }

    public void publishMessage(@NonNull final String topicName, @NonNull final String message) {
        getTopic(topicName).addMessage(MessageFactory.getNewMessage(message));
        log.info(message + " published to topic: " + topicName);
        new Thread(() -> topicNameToProcessorMap.get(topicName).publish()).start();
    }

    public void resetOffset(@NonNull final String topicName, @NonNull final String subscriberId, @NonNull final Integer newOffset) {
        List<Subscriber> subscriberList = getTopic(topicName).getSubscribers();
        for (Subscriber subscriber : subscriberList) {
            if (subscriber.getId().equals(subscriberId)) {
                subscriber.getOffset().set(newOffset);
                log.info(subscriber.getId() + " offset reset to: " + newOffset);
                new Thread(() -> topicNameToProcessorMap.get(topicName).startSubscriberWorker(subscriber)).start();
                break;
            }
        }
    }
}
