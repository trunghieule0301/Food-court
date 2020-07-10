package com.example.myapplication;

public class Food {
    private String ID;
    private String name;
    private String IDstall;
    private float Price;
    private String Url;

    public Food(String ID, String name, String IDstall, float price, String url) {
        this.ID = ID;
        this.name = name;
        this.IDstall = IDstall;
        this.Price = price;
        this.Url = url;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIDstall() {
        return IDstall;
    }

    public void setIDstall(String IDstall) {
        this.IDstall = IDstall;
    }

    public float getPrice() {
        return Price;
    }

    public void setPrice(Float price) {
        this.Price = price;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }
}
