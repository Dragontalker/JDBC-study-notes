package com.dragontalker.dao;

/*
此接口用于规范针对于customers表的常用操作
 */

import com.dragontalker.bean.Customer;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

public interface CustomerDAO {
    /**
     * @Description 将cust对象添加到数据库中
     * @param conn
     * @param cust
     */
    void insert(Connection conn, Customer cust);

    /**
     * @Description 根据指定的id, 删除表中的一条记录
     * @param conn
     * @param id
     */
    void deleteById(Connection conn, int id);

    /**
     * @Description 针对内存中的cust对象, 去修改数据表中指定的记录
     * @param conn
     * @param cust
     */
    void updateById(Connection conn, Customer cust);

    /**
     * @Description 根据指定的id查询得到对应的Customer对象
     * @param conn
     * @param id
     */
    void getCustomerById(Connection conn, int id);

    /**
     * @Description 查询表中的所有记录构成的集合
     * @param conn
     * @return
     */
    List<Customer> getAll(Connection conn);

    /**
     * @Description 返回数据表中数据的条目数
     * @param conn
     * @return
     */
    Long getCount(Connection conn);

    /**
     * @Description 返回数据表中最大的生日
     * @param conn
     * @return
     */
    Date getMaxBirth(Connection conn);
}
