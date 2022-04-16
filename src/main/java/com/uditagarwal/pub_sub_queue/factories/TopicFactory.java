package com.uditagarwal.pub_sub_queue.factories;

import com.uditagarwal.pub_sub_queue.handler.TopicProcessor;
import com.uditagarwal.pub_sub_queue.model.Topic;

import java.util.UUID;

public class TopicFactory {
    public static Topic getNewTopic(String topicName) {
        return new Topic(topicName, UUID.randomUUID().toString());
    }
    public static TopicProcessor getNewTopicProcessor(final Topic topic) {
        return new TopicProcessor(topic);
    }
}
