package com.dragontalker.preparedstatement.crud;

import com.dragontalker.connection.ConnectionTest;
import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
    public void testInsert() throws Exception{
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
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println(connection);

        //4.
        String sql = "insert into customers(name, email, birth) values(?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
    }
}
