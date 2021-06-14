package com.dragontalker.bean;

import java.sql.Date;

public class Order {
    private int orderId;
    private String orderName;
    private Date orderDate;

    public Order() {}

    public Order(int orderId, String orderName, Date orderDate) {
        this.orderId = orderId;
        this.orderName = orderName;
        this.orderDate = orderDate;
    }
}
