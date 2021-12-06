package com.example.demo.entity;

import java.util.concurrent.atomic.AtomicInteger;

public class User {

    private static final AtomicInteger count = new AtomicInteger(0);
    private int id;
    private String first_name;
    private String last_name;
    private String username;
    private String password;
    private String email;
    private String address;
    private String car_reg;
    private String mobile;
    private String car_model;
    private int balance;

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public User() {
        id = count.incrementAndGet();
    }

    public String getCar_model() {
        return car_model;
    }

    public void setCar_model(String car_model) {
        this.car_model = car_model;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCar_reg(String car_reg) {
        this.car_reg = car_reg;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public String getCar_reg() {
        return car_reg;
    }

    public String getEmail() {
        return email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getMobile() {
        return mobile;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}
