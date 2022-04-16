package com.uditagarwal.pub_sub_queue.factories;

import com.uditagarwal.pub_sub_queue.handler.TopicProcessor;
import com.uditagarwal.pub_sub_queue.model.InMemoryTopic;
import com.uditagarwal.pub_sub_queue.public_interface.Topic;

import java.util.UUID;

public class TopicFactory {
    public static Topic getNewTopic(String topicName) {
        return new InMemoryTopic(topicName);
    }
    public static TopicProcessor getNewTopicProcessor(final Topic topic) {
        return new TopicProcessor(topic);
    }
}
