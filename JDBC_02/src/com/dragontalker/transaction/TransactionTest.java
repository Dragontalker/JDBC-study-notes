package com.dragontalker.transaction;

import com.dragontalker.utils.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class TransactionTest {

    /*
    针对于数据表user_table来说:
    - AA用户给BB用户转账100
    - update user_table set balance = balance - 100 where user = 'AA';
    - update user_table set balance = balance + 100 where user = 'BB';
     */
    @Test
    public void testUpdate(){

    }

    //通用的增删改操作 --- version 1.0
    public int update(String sql, Object ... args) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, ps);
        }
        return 0;
    }
}
