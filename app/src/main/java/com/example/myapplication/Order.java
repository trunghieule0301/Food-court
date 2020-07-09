package com.example.myapplication;

public class Order {
    private String ID;
    private String IDstall;
    private int IDcustomer;
    private Float Total_price;
    private int Pirce;
    private String Status;
    private String date;

    public Order(String ID, String IDstall, int IDcustomer, Float total_price, int pirce, String status, String date) {
        this.ID = ID;
        this.IDstall = IDstall;
        this.IDcustomer = IDcustomer;
        this.Total_price = total_price;
        this.Pirce = pirce;
        this.Status = status;
        this.date = date;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getIDstall() {
        return IDstall;
    }

    public void setIDstall(String IDstall) {
        this.IDstall = IDstall;
    }

    public int getIDcustomer() {
        return IDcustomer;
    }

    public void setIDcustomer(int IDcustomer) {
        this.IDcustomer = IDcustomer;
    }

    public Float getTotal_price() {
        return Total_price;
    }

    public void setTotal_price(Float total_price) {
        this.Total_price = total_price;
    }

    public int getPirce() {
        return Pirce;
    }

    public void setPirce(int pirce) {
        this.Pirce = pirce;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        this.Status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
