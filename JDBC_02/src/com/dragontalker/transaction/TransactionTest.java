package com.dragontalker.transaction;

import com.dragontalker.utils.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;

/*
1. 什么叫数据库事务?
    - 事务: 一组逻辑单元, 使数据从一种状态变换到另一种状态
        > 一组逻辑单元: 一个或多个DML操作
 */
public class TransactionTest {

    /*
    针对于数据表user_table来说:
    - AA用户给BB用户转账100
    - update user_table set balance = balance - 100 where user = 'AA';
    - update user_table set balance = balance + 100 where user = 'BB';
     */
    @Test
    public void testUpdate(){
        String sql1 = "update user_table set balance = balance - 100 where user = ?";
        update(sql1, "AA");

        //模拟网路异常
        //System.out.println(10 / 0);

        String sql2 = "update user_table set balance = balance + 100 where user = ?";
        update(sql2, "BB");

        System.out.println("转账成功");
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
