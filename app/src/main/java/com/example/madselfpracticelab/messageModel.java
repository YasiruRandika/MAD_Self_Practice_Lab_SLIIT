package com.example.madselfpracticelab;

public class messageModel {
    private String subject, message;
    private int messageId;

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public messageModel(String subject, String message) {
        this.subject = subject;
        this.message = message;
    }

    public messageModel() {
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
