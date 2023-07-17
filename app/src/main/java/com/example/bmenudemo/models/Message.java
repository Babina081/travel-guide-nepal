package com.example.bmenudemo.models;

public class Message {
    private String text;
    private String timestamp;
    private String sender;

    public Message(String  text, String  sender) {
        this.text = text;
        this.sender = sender;

    }

    public Message(String text, String sender, String timestamp) {
        this.text = text;
        this.sender = sender;
        this.timestamp = timestamp;
    }

    public String  getText() {
        return text;
    }

    public String  getTimestamp() {
        return timestamp;
    }

    public String getSender() {
        return sender;
    }

}
