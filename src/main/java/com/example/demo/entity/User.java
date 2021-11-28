package com.example.demo.entity;

public class User {

    private String first_name;
    private String last_name;
    private String username;
    private String password;
    private String email;
    private String address;
    private String car_reg;
    private String mobile;

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
