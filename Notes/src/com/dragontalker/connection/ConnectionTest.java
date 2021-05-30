package com.dragontalker.connection;

import org.junit.Test;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionTest {

    //方式一:
    @Test
    public void test1() throws SQLException {
        //获取厂商的driver
        Driver driver = new com.mysql.jdbc.Driver();

        //Tomcat: url:http://localhost:8080/gmall/keyboard.jpg
        //jdbc:sql: 协议
        //localhost: ip地址
        //3306: 默认mysql的端口号
        //test: test数据
        String url = "jdbc:sql://localhost:3306/test";
        //将用户名和密码封装在Properties中
        Properties info = new Properties();
        info.setProperty("user", "root");
        info.setProperty("password", "password");

        Connection conn = driver.connect(url, info);

        System.out.println(conn);
    }

    //方式二: 对方式一进行迭代:
    // - 在如下的程序中不出现第三方的api
    // - 使程序有更好的可移植性
    @Test
    public void test2() throws Exception {
        //1. 获取Driver实现对象, 使用反射
        Class clazz = Class.forName("com.mysql.jdbc.Driver");
        Driver driver = (Driver) clazz.getDeclaredConstructor().newInstance();

        //2. 提供要连接的数据库
        String url = "jdbc:mysql://localhost:3306/test?characterEncoding=latin1";

        //3. 提供连接需要的用户名和密码
        Properties info = new Properties();
        info.setProperty("user", "root");
        info.setProperty("password", "abc123");

        //4. 获取连接
        Connection conn = driver.connect(url, info);
        System.out.println(conn);
    }

    //方式三: 使用DriverManager替换Driver
    @Test
    public void testConnection() throws Exception {
        //1. 获取Driver实现类的对象
        Class clazz = Class.forName("com.mysql.jdbc.Driver");
        Driver driver = (Driver) clazz.newInstance();

        //2. 提供另外三个连接的基本信息
        String url = "jdbc:mysql://localhost:3306/test?characterEncoding=latin1";
        String user = "root";
        String password = "sql23248265YT";

        //3. 注册驱动
        DriverManager.registerDriver(driver);

        //4. 获取连接
        DriverManager.getConnection(url, user, password);
    }

}
