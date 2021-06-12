package com.dragontalker.preparedstatement.crud;

import com.dragontalker.connection.ConnectionTest;
import com.dragontalker.util.JDBCUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Properties;

/**
 * 使用PreparedStatement来替换Statement, 实现对数据表的增删改查操作
 *
 * 增删改(广义上的修改): 没有return
 * 查: 有return
 */

public class PreparedStatementUpdateTest {
    @Test
    public void testUpdate1() {
        String sql1 = "delete from customers where id = ?";
        update(sql1, 19);

        String sql2 = "update from `order` set order_name = ? where order_id = ?";
        update(sql2, "DD", "2");
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

    //修改customers表的一条记录
    @Test
    public void testUpdate() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            //1. 获取数据库的连接
            conn = JDBCUtils.getConnection();

            //2. 预编译sql语句, 返回PreparedStatement的实例
            String sql = "update customers set name = ? where id = ?";
            ps = conn.prepareStatement(sql);

            //3. 填充占位符
            ps.setObject(1, "SunWuKong");
            ps.setObject(2, 19);

            //4. 执行
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //5. 资源的关闭
            JDBCUtils.closeResource(conn, ps);
        }
    }

    //向customers表中添加一条记录
    @Test
    public void testInsert() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            //1. 读取配置文件中的4个基本信息
            InputStream is = ConnectionTest.class.getClassLoader().getResourceAsStream("jdbc.properties");

            Properties props = new Properties();
            props.load(is);

            String url = props.getProperty("url");
            String user = props.getProperty("user");
            String password = props.getProperty("password");
            String driverClass = props.getProperty("driverClass");

            //2. 加载驱动
            Class.forName(driverClass);

            //3. 获取连接
            connection = DriverManager.getConnection(url, user, password);
            System.out.println(connection);

            //4. 预编译sql语句, 返回PrepareStatement的实例
            String sql = "insert into customers(name, email, birth) values(?,?,?)";
            ps = connection.prepareStatement(sql);

            //5. 填充占位符
            ps.setString(1, "NeZha");
            ps.setString(2, "nazha@gmail.com");

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date = sdf.parse("1000-01-01");
            ps.setDate(3, new Date(date.getTime()));

            //6. 执行sql操作
            ps.execute();
        } catch (IOException | ClassNotFoundException | SQLException | ParseException e) {
            e.printStackTrace();
        } finally {
            //7. 资源的关闭
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }
}
