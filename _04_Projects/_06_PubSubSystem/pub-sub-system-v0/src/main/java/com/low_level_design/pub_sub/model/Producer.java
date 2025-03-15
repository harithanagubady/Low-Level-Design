package com.low_level_design.pub_sub.model;

public class Producer implements IProducer {

    private final int id;
    private final Broker broker;

    private Producer() {
        this.id = 0;
        this.broker = new Broker();
    }

    public Producer(int id, Broker broker) {
        this.id = id;
        this.broker = broker;
    }

    @Override
    public void publish(int topicId, Message message) {
        System.out.println("Producer " + id + " attempting to publish message \"" + message + "\"");
        broker.publish(topicId, message);
    }
}
