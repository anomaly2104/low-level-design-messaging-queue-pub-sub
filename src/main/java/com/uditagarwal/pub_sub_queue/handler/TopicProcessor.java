package com.uditagarwal.pub_sub_queue.handler;

import com.uditagarwal.pub_sub_queue.interfaces.Subscriber;
import com.uditagarwal.pub_sub_queue.interfaces.Topic;
import lombok.Getter;
import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;

@Getter
public class TopicProcessor {
    private final Topic topic;
    private final Map<String, SubscriberWorker> subscriberWorkers;

    public TopicProcessor(@NonNull final Topic topic) {
        this.topic = topic;
        subscriberWorkers = new HashMap<>();
    }

    public void publish() {
        for (Subscriber subscriber : topic.getSubscribers()) {
            startSubscriberWorker(subscriber);
        }
    }

    public void startSubscriberWorker(@NonNull final Subscriber subscriber) {
        final String subscriberId = subscriber.getId();
        if (!subscriberWorkers.containsKey(subscriberId)) {
            final SubscriberWorker subscriberWorker = new SubscriberWorker(topic, subscriber);
            subscriberWorkers.put(subscriberId, subscriberWorker);
            new Thread(subscriberWorker).start();
        }
        final SubscriberWorker subscriberWorker = subscriberWorkers.get(subscriberId);
        subscriberWorker.wakeUpIfNeeded();
    }
}
