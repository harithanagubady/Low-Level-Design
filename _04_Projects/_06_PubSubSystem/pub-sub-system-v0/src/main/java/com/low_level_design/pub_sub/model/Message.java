package com.low_level_design.pub_sub.model;

public class Message {

    private int messageId;
    private String message;

    private long timestamp;

    public Message(int messageId, String message, long timestamp) {
        this.messageId = messageId;
        this.message = message;
        this.timestamp = timestamp;
    }

    public Message(int i, String s) {
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "message:{" + " id= " + messageId + ", message= " + message + ", timestamp= " + timestamp;
    }
}
