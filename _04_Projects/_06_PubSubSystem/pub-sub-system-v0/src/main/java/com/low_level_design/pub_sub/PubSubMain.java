package com.low_level_design.pub_sub;

import com.low_level_design.pub_sub.model.*;

public class PubSubMain {

    public static void main(String[] args) throws InterruptedException {
        Broker broker = new Broker();
        Topic topic = broker.createTopic(1, 10000);
        int topicId = topic.getId();

        IProducer bob = new Producer(1, broker);
        IConsumer alice = new Consumer(1, broker);
        broker.subscribe(topicId, alice);

        Message message = new Message(1, "Breaking News: AI Advancements!", System.currentTimeMillis());
        bob.publish(topicId, message);

        System.out.println("Current messages in topic: " + topicId + ": \n" + topic.getMessages());
        Thread.sleep(10000); // Wait for cleanup to trigger
        System.out.println("Current messages in topic: " + topicId + ": \n" + topic.getMessages());
    }
}
