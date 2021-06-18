package com.dragontalker.exer;

import com.dragontalker.util.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

//课后练习1
public class Exercise1Test {

    @Test
    public void testInsert() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入用户名");
        String name = scanner.next();
    }

    //通用的增删改操作
    public void update(String sql, Object ... args) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, ps);
        }
    }
}
