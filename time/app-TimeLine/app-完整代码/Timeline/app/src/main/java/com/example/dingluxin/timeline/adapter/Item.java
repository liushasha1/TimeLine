package com.example.dingluxin.timeline.adapter;

public class Item {
    private final String ID;
    private final String userName;
    private final String userId;
    private final String content;
    private String image;
    private final String time;


    public Item(String ID, String userName, String userId, String content, String image, String time)
    {
        this.ID = ID;
        this.userName=userName;
        this.userId=userId;
        this.content=content;
        this.image=image;
        this.time=time;
    }
    public Item(String ID,String userName, String userId, String content, String time)
    {
        this.ID = ID;
        this.userName=userName;
        this.userId=userId;
        this.content=content;
        this.time=time;
    }

    public String getID()
    {
        return ID;
    }
    public String getUserName()
    {
        return userName;
    }
    public String getContent()
    {
        return content;
    }
    public String getImage()
    {
        return image;
    }
    public String getTime()
    {
        return time.substring(0,19);
    }
}
