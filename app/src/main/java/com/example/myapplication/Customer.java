package com.example.myapplication;

public class Customer {
    private int ID;
    private String Account;
    private String Name;
    private String Email;
    private String Password;
    private int Age;
    private String Sex;
    private String Address;

    public Customer(int ID, String account, String name, String email, String password, int age, String sex, String address) {
        this.ID = ID;
        Account = account;
        Name = name;
        Email = email;
        Password = password;
        Age = age;
        Sex = sex;
        Address = address;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getAccount() {
        return Account;
    }

    public void setAccount(String account) {
        Account = account;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }
}
