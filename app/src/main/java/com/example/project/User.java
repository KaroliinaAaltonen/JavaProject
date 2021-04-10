package com.example.project;

public class User {

    public User(String username, byte[] password)
    {
        this.username = username;
        this.password = password;
    }

    private String username;
    private byte[] password;

    public String getUsername()
    {
        return username;
    }
    public void setUsername(String username)
    {
        this.username = username;
    }

    public byte[] getPassword()
    {
        return password;
    }
    public void setPassword(byte[] password)
    {
        this.password = password;
    }
}