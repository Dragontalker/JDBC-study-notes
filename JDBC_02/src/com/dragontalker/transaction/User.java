package com.dragontalker.transaction;

public class User {
    private String user;
    private String password;
    private double balance;

    public User() {}

    public User(String user, String password, double balance) {
        this.user = user;
        this.password = password;
        this.balance = balance;
    }
}
