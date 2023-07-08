package com.anuar.chatapp;

public class message {
    private String text;
    private String name;
    private String imageUrl;

    private String sender;
    private boolean isMine;
    private String receiver;
    public message(){

    }
    public message(String text, String name, String imageUrl, String sender, String receiver,boolean isMine) {
        this.text = text;
        this.name = name;
        this.imageUrl = imageUrl;
        this.sender = sender;
        this.receiver = receiver;
        this.isMine=isMine;
    }

    public boolean isMine() {
        return isMine;
    }

    public void setMine(boolean mine) {
        isMine = mine;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getText() {
        return text;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
