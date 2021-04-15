package com.example.project;

public class User {

    public User(String username, byte[] password)
    {
        this.username = username;
        this.password = password;
    }

    private String username;
    private byte[] password;
    private String birthday;
    private float height;
    private float weight;
    private String gender;
    private String dietaryInfo;
    private String smokingInfo;

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

    public String getBirthday()
    {
        return birthday;
    }
    public void setBirthday(String birthday)
    {
        this.birthday = birthday;
    }

    public float getHeight()
    {
        return height;
    }
    public void setHeight(float height)
    {
        this.height = height;
    }

    public float getWeight()
    {
        return weight;
    }
    public void setWeight(float weight)
    {
        this.weight = weight;
    }

    public String getGender()
    {
        return gender;
    }
    public void setGender(String gender)
    {
        this.gender = gender;
    }

    public String getDietaryInfo()
    {
        return dietaryInfo;
    }
    public void setDietaryInfo(String dietaryInfo)
    {
        this.dietaryInfo = dietaryInfo;
    }

    public String getSmokingInfo()
    {
        return smokingInfo;
    }
    public void setSmokingInfo(String smokingInfo)
    {
        this.smokingInfo = smokingInfo;
    }
}