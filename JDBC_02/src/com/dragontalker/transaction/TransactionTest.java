package com.dragontalker.transaction;

import com.dragontalker.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class TransactionTest {

    public void update(String sql, Object ... args) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, ps);
        }
    }
}
