package com.dragontalker.exer;

import com.dragontalker.util.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;

//课后练习2
public class Exercise2Test {

    //问题1: 向ExamStudent表添加一条记录
    @Test
    public void testInsert() {

    }

    //通用的增删改操作
    public int update(String sql, Object ... args) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            /*
            ps.execute():
            - 如果执行的查询操作, 有返回结果, 则此方法返回true;
            - 如果执行的是增、删、改、操作, 没有返回结果, 则此方法返回false;
             */
            //方式一:
            //return ps.execute();
            //方式二:
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, ps);
        }
        return 0;
    }
}
