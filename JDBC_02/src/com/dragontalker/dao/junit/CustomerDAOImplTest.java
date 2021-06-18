package com.dragontalker.dao.junit;

import com.dragontalker.bean.Customer;
import com.dragontalker.dao.CustomerDAOImpl;
import com.dragontalker.utils.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Date;

public class CustomerDAOImplTest {

    private CustomerDAOImpl dao = new CustomerDAOImpl();

    @Test
    public void testInsert() {
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            Customer cust = new Customer(1, "YuXiaoFei", "xiaofei@126.com", new Date(43534646435L));
            dao.insert(conn, cust);
            System.out.println("添加成功");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
    }

    @Test
    public void testDeleteById() {
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            dao.deleteById(conn, 13);
            System.out.println("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
    }
}
