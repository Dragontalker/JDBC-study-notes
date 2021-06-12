package com.dragontalker.preparedstatement.crud;

import com.dragontalker.util.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * 针对于Customer表的查询操作
 */

public class CustomerForQuery {

    @Test
    public void testQuery1() {
        Connection conn = JDBCUtils.getConnection();
        String sql = "select id, name, email, birth from customers where id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);

    }
}
