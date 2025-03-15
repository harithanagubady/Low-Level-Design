package com.low_level_design.pub_sub.model;

import java.util.*;

public class Topic {

    private int id;
    private Queue<Message> messages = new LinkedList<>();
    List<IConsumer> consumers = new ArrayList<>();

    Map<Message, Set<IConsumer>> acks = new HashMap<>();
    private final long retentionInMillis;

    public Topic(int id, long retentionInMillis) {
        this.id = id;
        this.retentionInMillis = retentionInMillis;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Queue<Message> getMessages() {
        return messages;
    }

    public void setMessages(Queue<Message> messages) {
        this.messages = messages;
    }

    public List<IConsumer> getConsumers() {
        return consumers;
    }

    public void setConsumers(List<IConsumer> consumers) {
        this.consumers = consumers;
    }


    public Map<Message, Set<IConsumer>> getAcks() {
        return acks;
    }

    public void subscribe(IConsumer consumer) {
        consumers.add(consumer);
    }

    public void addMessage(Message message) {
        System.out.println("Message published into topic : " + id);
        messages.offer(message);
        notifyConsumers(message);
    }

    private void notifyConsumers(Message message) {
        for (IConsumer consumer : consumers) {
            consumer.consume(id, message);
        }
    }

    public void acknowledgeMessage(IConsumer consumer, Message message) {
        Set<IConsumer> ackConsumers = this.acks.getOrDefault(message, new HashSet<>());
        ackConsumers.add(consumer);
        this.acks.put(message, ackConsumers);
        System.out.println("acks======" + acks);
    }

    public void removeExpiredMessages() {
        long currentTime = System.currentTimeMillis();
        Set<Message> messagesToBeRemoved = new HashSet<>();
        for (Message message : messages) {
            boolean canRemove = checkRemoveMessageCondition(message, currentTime);
            if (canRemove) {
                System.out.println("Removing messages at timestamp: " + currentTime);
                acks.remove(message);
                messagesToBeRemoved.add(message);
            }
        }
        messages.removeIf(messagesToBeRemoved::contains);
    }

    private boolean checkRemoveMessageCondition(Message message, long currentTime) {
        return isExpired(currentTime, message.getTimestamp())
                && acks.getOrDefault(message, new HashSet<>()).size() == consumers.size();
    }

    private boolean isExpired(long currentTime, long timestamp) {
        return currentTime - timestamp >= retentionInMillis;
    }
}
