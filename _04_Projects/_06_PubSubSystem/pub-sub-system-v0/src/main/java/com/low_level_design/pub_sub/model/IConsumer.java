package com.low_level_design.pub_sub.model;

public interface IConsumer {

    void consume(int topicId, Message message);
}
