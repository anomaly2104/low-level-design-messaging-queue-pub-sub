package com.uditagarwal;

import com.uditagarwal.pub_sub_queue.InMemoryQueue;
import com.uditagarwal.pub_sub_queue.model.Message;
import com.uditagarwal.pub_sub_queue.public_interface.Queue;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Application {

    public static void main(String[] args) throws InterruptedException {
        log.info("starting application");
        final Queue queue = new InMemoryQueue();
        final String topicName1 = "t1", topicName2 = "t2";

        queue.createTopic(topicName1);
        queue.createTopic(topicName2);
        final DummySubscriber sub1 = new DummySubscriber("sub1", 10000);
        final DummySubscriber sub2 = new DummySubscriber("sub2", 10000);
        queue.addSubscriber(sub1, topicName1);
        queue.addSubscriber(sub2, topicName1);
        final DummySubscriber sub3 = new DummySubscriber("sub3", 5000);
        queue.addSubscriber(sub3, topicName2);

        queue.publishMessage(topicName1, new Message("m1"));
        queue.publishMessage(topicName1, new Message("m2"));

        queue.publishMessage(topicName2, new Message("m3"));

        Thread.sleep(15000);
        queue.publishMessage(topicName2, new Message("m4"));
        queue.publishMessage(topicName1, new Message("m5"));

        queue.resetOffset(topicName1, sub1, 0);
    }
}
