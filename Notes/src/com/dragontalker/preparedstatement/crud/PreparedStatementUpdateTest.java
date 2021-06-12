package com.dragontalker.preparedstatement.crud;

import com.dragontalker.connection.ConnectionTest;
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

    //向customers表中添加一条记录
    @Test
    public void testInsert() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {


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
