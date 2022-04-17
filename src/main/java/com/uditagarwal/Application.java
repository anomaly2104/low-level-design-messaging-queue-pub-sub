package com.uditagarwal;

import com.uditagarwal.pub_sub_queue.factories.QueueFactory;
import com.uditagarwal.pub_sub_queue.interfaces.Queue;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Application {

    public static void main(String[] args) throws InterruptedException {
        log.info("Started application");
        final Queue queue = QueueFactory.getInMemoryQueue();
        final String topicName1 = "t1", topicName2 = "t2";

        queue.createTopic(topicName1);
        queue.createTopic(topicName2);

        queue.addSubscriber("sub1", topicName1, 10000);
        queue.addSubscriber("sub2", topicName1, 10000);
        queue.addSubscriber("sub3", topicName2, 5000);

        queue.publishMessage(topicName1, "m1");
        queue.publishMessage(topicName1, "m2");
        queue.publishMessage(topicName2, "m3");

        log.info("Sleeping for 15 seconds ...");
        Thread.sleep(15000);
        log.info("Woke up after sleeping for 15 seconds ...");

        queue.publishMessage(topicName2, "m4");
        queue.publishMessage(topicName1, "m5");

        queue.resetOffset(topicName1, "sub1", 0);
        log.info("Stopped application");
    }
}
