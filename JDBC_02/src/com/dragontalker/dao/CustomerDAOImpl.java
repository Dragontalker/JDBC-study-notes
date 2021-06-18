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
        String sql = "delete from customers where id = ?";
        update(conn, sql, id);
    }

    @Override
    public void updateById(Connection conn, Customer cust) {
        String sql = "update customers set name = ?, email = ?, birth = ? where id = ?";
        update(conn, sql, cust.getName(), cust.getEmail(), cust.getBirth(), cust.getId());
    }

    @Override
    public Customer getCustomerById(Connection conn, int id) {
        String sql = "select id, name, email, birth from customers where id = ?";
        return getInstance(conn, Customer.class, sql, id);
    }

    @Override
    public List<Customer> getAll(Connection conn) {
        String sql = "select id, name, email, birth from customers";
        return getForList(conn, Customer.class, sql);
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
