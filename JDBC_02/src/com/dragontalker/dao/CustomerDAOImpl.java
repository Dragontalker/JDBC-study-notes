package com.dragontalker.dao;

import com.dragontalker.bean.Customer;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

public class CustomerDAOImpl extends BaseDAO implements CustomerDAO{
    @Override
    public void insert(Connection conn, Customer cust) {
        String sql = "insert into customers(name, email, birth) values(?, ?, ?)";
        update(conn, sql, cust.getName(), cust.getEmail(), cust.getBirth());
    }

    @Override
    public void deleteById(Connection conn, int id) {

    }

    @Override
    public void updateById(Connection conn, Customer cust) {

    }

    @Override
    public void getCustomerById(Connection conn, int id) {

    }

    @Override
    public List<Customer> getAll(Connection conn) {
        return null;
    }

    @Override
    public Long getCount(Connection conn) {
        return null;
    }

    @Override
    public Date getMaxBirth(Connection conn) {
        return null;
    }
}
