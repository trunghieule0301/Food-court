package com.example.myapplication;

public class Stall{
    private String ID;
    private String Name;
    private String url;

    public Stall(String ID, String name, String url) {
        this.ID = ID;
        this.Name = name;
        this.url = url;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

