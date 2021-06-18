package com.dragontalker.blob;

import com.dragontalker.util.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * @Description 使用PreparedStatement实现批量数据的操作
 *
 * update, delete本身就具有批量操作的效果
 * 此时的批量操作, 主要指的是批量插入, 使用PreparedStatement如何实现更高效的批量插入?
 *
 * 题目: 要求向goods表中插入2000条数据
 *
 * CREATE TABLE goods(
 * 	id INT PRIMARY KEY AUTO_INCREMENT,
 *     NAME VARCHAR(25)
 * );
 *
 *
 * 方式一: 使用Statement
 * Connection conn = JDBCUtils.getConnection();
 * Statement st = conn.createStatement();
 * for (int i = 0; i <= 20000; i++) {
 *     String sql = "insert into goods(name) values('name_" + i + "')";
 *     st.execute(sql)
 * }
 */

public class InsertTest {

    //批量插入的方式二
    @Test
    public void testInsertMany() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {

            long start = System.currentTimeMillis();

            conn = JDBCUtils.getConnection();
            String sql = "insert into goods(name) values(?)";
            ps = conn.prepareStatement(sql);
            for (int i = 0; i <= 2000; i++) {
                ps.setObject(1, "name_" + i);
                ps.execute();
            }

            long end = System.currentTimeMillis();

            System.out.println("花费的时间为: " + (end - start) + "ms");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, ps);
        }
    }

    /*
    批量插入的方式三:
    1. addBatch(),
     */
    @Test
    public void testInsertMany1() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {

            long start = System.currentTimeMillis();

            conn = JDBCUtils.getConnection();
            String sql = "insert into goods(name) values(?)";
            ps = conn.prepareStatement(sql);
            for (int i = 0; i <= 2000; i++) {
                ps.setObject(1, "name_" + i);

                //1. "攒"sql"
                ps.addBatch();

                if (i % 500 == 0) {
                    //2. 指定batch
                    ps.execute();

                    //3. 清空batch
                    ps.clearBatch();
                }

            }

            long end = System.currentTimeMillis();

            System.out.println("花费的时间为: " + (end - start) + "ms");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, ps);
        }
    }
}
