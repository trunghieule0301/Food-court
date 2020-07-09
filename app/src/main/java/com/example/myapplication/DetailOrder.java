package com.example.myapplication;

public class DetailOrder {
    private String IDorder;
    private String IDstall;
    private int Number;
    private Float Price;

    public DetailOrder(String IDorder, String IDstall, int number, Float price) {
        this.IDorder = IDorder;
        this.IDstall = IDstall;
        this.Number = number;
        this.Price = price;
    }

    public String getIDorder() {
        return IDorder;
    }

    public void setIDorder(String IDorder) {
        this.IDorder = IDorder;
    }

    public String getIDstall() {
        return IDstall;
    }

    public void setIDstall(String IDstall) {
        this.IDstall = IDstall;
    }

    public int getNumber() {
        return Number;
    }

    public void setNumber(int number) {
        this.Number = number;
    }

    public Float getPrice() {
        return Price;
    }

    public void setPrice(Float price) {
        this.Price = price;
    }
}
