package com.dragontalker.dao;

/*
此接口用于规范针对于customers表的常用操作
 */

import com.dragontalker.bean.Customer;

import java.sql.Connection;

public interface CustomerDAO {
    /**
     * @Description 将cust对象添加到数据库中
     * @param conn
     * @param cust
     */
    void insert(Connection conn, Customer cust);
}
