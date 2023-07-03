package com.anuar.chatapp;

public class User {
    private String name;
    private String email;
    private String id;
    private int AvatarMockUpResource;

    public User(String name, String email, String id,int AvatarMockUpResource) {
        this.name = name;
        this.email = email;
        this.id = id;
        this.AvatarMockUpResource=AvatarMockUpResource;
    }
    public User(){

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAvatarMockUpResource(int avatarMockUpResource) {
        this.AvatarMockUpResource = avatarMockUpResource;
    }

    public int getAvatarMockUpResource() {
        return AvatarMockUpResource;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getId() {
        return id;
    }
}
