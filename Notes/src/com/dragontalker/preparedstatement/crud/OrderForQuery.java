package com.dragontalker.preparedstatement.crud;

import com.dragontalker.bean.Order;
import com.dragontalker.util.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @Description 针对orders表的查询操作
 */

public class OrderForQuery {

    @Test
    public void testQuery1() throws Exception{
        Connection conn = JDBCUtils.getConnection();
        String sql = "select order_id, order_name, order_date from `orders` where id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setObject(1, 1);

        ResultSet rs = ps.executeQuery();
        if(rs.next()) {
            int id = rs.getInt(1);
            String name = rs.getString(2);
            Date date = rs.getDate(3);

            Order order = new Order(id, name, date);
            System.out.println(order);
        }

        JDBCUtils.closeResource(conn, ps, rs);
    }
}
