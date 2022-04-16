package com.uditagarwal.pub_sub_queue.model;

import com.uditagarwal.pub_sub_queue.public_interface.Subscriber;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import java.util.concurrent.atomic.AtomicInteger;

@Getter
@AllArgsConstructor
public class TopicSubscriber {
    private final AtomicInteger offset;
    private final Subscriber subscriber;

    public TopicSubscriber(@NonNull final Subscriber subscriber) {
        this.subscriber = subscriber;
        this.offset = new AtomicInteger(0);
    }
}
