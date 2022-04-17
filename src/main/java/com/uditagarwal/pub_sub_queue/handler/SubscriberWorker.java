package com.uditagarwal.pub_sub_queue.handler;

import com.uditagarwal.pub_sub_queue.interfaces.Subscriber;
import com.uditagarwal.pub_sub_queue.models.Message;
import com.uditagarwal.pub_sub_queue.interfaces.Topic;
import lombok.Getter;
import lombok.NonNull;
import lombok.SneakyThrows;

@Getter
public class SubscriberWorker implements Runnable {

    private final Topic topic;
    private final Subscriber subscriber;

    public SubscriberWorker(@NonNull final Topic topic, @NonNull final Subscriber subscriber) {
        this.topic = topic;
        this.subscriber = subscriber;
    }

    @SneakyThrows
    @Override
    public void run() {
        synchronized (subscriber) {
            do {
                int curOffset = subscriber.getOffset().get();
                while (curOffset >= topic.getMessages().size()) {
                    subscriber.wait();
                }
                Message message = topic.getMessages().get(curOffset);
                subscriber.consume(message);

                // We cannot just increment here since subscriber offset can be reset while it is consuming. So, after
                // consuming we need to increase only if it was previous one.
                subscriber.getOffset().compareAndSet(curOffset, curOffset + 1);
            } while (true);
        }
    }

    synchronized public void wakeUpIfNeeded() {
        synchronized (subscriber) {
            subscriber.notify();
        }
    }
}
