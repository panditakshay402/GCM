package com.example.gcm;

public class Message {
    private String sender;
    private String receiver;
    private long timestamp;
    private String content;

    // Default constructor (required for Firebase)
    public Message() {
    }

    public Message(String sender, String receiver, long timestamp, String content) {
        this.sender = sender;
        this.receiver = receiver;
        this.timestamp = timestamp;
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getContent() {
        return content;
    }

    public boolean isRead() {
        return false;
    }

    public void setRead(boolean b) {

    }
}
