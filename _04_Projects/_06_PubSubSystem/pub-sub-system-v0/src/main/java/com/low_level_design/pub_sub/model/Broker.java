package com.low_level_design.pub_sub.model;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Broker {

    public static final int DEFAULT_RETENTION_IN_MILLIS = 15;

    Map<Integer, Topic> topics = new HashMap<>();
    private final ScheduledExecutorService cleanupExecutor = Executors.newSingleThreadScheduledExecutor();

    public Broker () {
        cleanupExecutor.scheduleAtFixedRate(this::cleanup, 5, 50, TimeUnit.MILLISECONDS);
    }

    public Topic createTopic (int id) {
        if (topics.containsKey(id)) {
            System.out.println("Topic already exists");
        }
        return topics.computeIfAbsent(id, topic -> new Topic(id, DEFAULT_RETENTION_IN_MILLIS));
    }

    public Topic createTopic (int id, long retentionInMillis) {
        if (topics.containsKey(id)) {
            System.out.println("Topic already exists");
        }
        Topic topic = topics.computeIfAbsent(id, t -> new Topic(id, retentionInMillis));
        topics.put(topic.getId(), topic);
        return topic;
    }

    public Topic getTopic(int id) {
        return topics.get(id);
    }

    public void cleanup() {
        for (Topic topic : topics.values()) {
            topic.removeExpiredMessages();
        }
    }

    public void subscribe (int topicId, IConsumer consumer) {
        if (topics.containsKey(topicId)) {
            topics.get(topicId).subscribe(consumer);
        } else {
            throw new IllegalArgumentException("Topic " + topicId + " doesn't exist");
        }
    }

    public void publish(int topicId, Message message) {
        if (topics.containsKey(topicId)) {
            topics.get(topicId).addMessage(message);
        } else {
            throw new IllegalArgumentException("Topic " + topicId + " doesn't exist");
        }
    }

    public synchronized void acknowledgeMessage(int topicId, IConsumer consumer, Message message) {
        if (topics.containsKey(topicId)) {
            topics.get(topicId).acknowledgeMessage(consumer, message);
        } else {
            throw new IllegalArgumentException("Topic " + topicId + " doesn't exist");
        }
    }
}
