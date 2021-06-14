package com.dragontalker.preparedstatement.crud;

import com.dragontalker.util.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
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

    }
}
