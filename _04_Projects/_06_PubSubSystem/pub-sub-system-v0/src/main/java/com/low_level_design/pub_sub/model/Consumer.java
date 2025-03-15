package com.low_level_design.pub_sub.model;

public class Consumer implements IConsumer {

    private final int id;
    private final Broker broker;

    public Consumer() {
        this.id = 0;
        this.broker = new Broker();
    }

    public Consumer(int id, Broker broker) {
        this.id = id;
        this.broker = broker;
    }

    @Override
    public void consume(int topicId, Message message) {
        System.out.println("Consumer " + id + " consumed message \"" + message.getMessage() + "\"");
        broker.acknowledgeMessage(topicId, this, message);
    }
}
