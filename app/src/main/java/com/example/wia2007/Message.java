package com.example.wia2007;

public class Message {
    String message;
    String sender;

    public static String SENT_BY_USER = "user";
    public static String SENT_BY_AI_CHATBOT = "ai";

    public Message(String message, String sender) {
        this.message = message;
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
