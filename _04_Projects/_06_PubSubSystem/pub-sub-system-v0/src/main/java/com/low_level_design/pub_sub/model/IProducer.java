package com.low_level_design.pub_sub.model;

public interface IProducer {

    void publish (int topicId, Message content);
}
