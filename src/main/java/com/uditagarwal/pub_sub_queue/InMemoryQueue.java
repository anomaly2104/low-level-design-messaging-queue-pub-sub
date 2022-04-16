package com.uditagarwal.pub_sub_queue;

import com.uditagarwal.pub_sub_queue.factories.SubscriberFactory;
import com.uditagarwal.pub_sub_queue.factories.TopicFactory;
import com.uditagarwal.pub_sub_queue.handler.TopicProcessor;
import com.uditagarwal.pub_sub_queue.model.Message;
import com.uditagarwal.pub_sub_queue.model.InMemoryTopic;
import com.uditagarwal.pub_sub_queue.model.TopicSubscriber;
import com.uditagarwal.pub_sub_queue.public_interface.Queue;
import com.uditagarwal.pub_sub_queue.public_interface.Subscriber;
import com.uditagarwal.pub_sub_queue.public_interface.Topic;
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

    public void addSubscriber(@NonNull final Subscriber subscriber, @NonNull final String topicName) {
        getTopic(topicName).addSubscriber(SubscriberFactory.getTopicSubscriber(subscriber));
        log.info(subscriber.getId() + " subscribed to topic: " + topicName);
    }

    private Topic getTopic(String topicName) {
        return topicNameToProcessorMap.get(topicName).getTopic();
    }

    public void publishMessage(@NonNull final String topicName, @NonNull final Message message) {
        getTopic(topicName).addMessage(message);
        log.info(message.getMsg() + " published to topic: " + topicName);
        new Thread(() -> topicNameToProcessorMap.get(topicName).publish()).start();
    }

    public void resetOffset(@NonNull final String topicName, @NonNull final Subscriber subscriber, @NonNull final Integer newOffset) {
        List<TopicSubscriber> topicSubscriberList = getTopic(topicName).getSubscribers();
        for (TopicSubscriber topicSubscriber : topicSubscriberList) {
            if (topicSubscriber.getSubscriber().equals(subscriber)) {
                topicSubscriber.getOffset().set(newOffset);
                log.info(topicSubscriber.getSubscriber().getId() + " offset reset to: " + newOffset);
                new Thread(() -> topicNameToProcessorMap.get(topicName).startSubscriberWorker(topicSubscriber)).start();
                break;
            }
        }
    }
}
