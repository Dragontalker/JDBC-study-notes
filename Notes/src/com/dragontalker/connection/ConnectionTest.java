package com.dragontalker.connection;

import org.junit.Test;

import java.io.InputStream;
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
        Class<?> clazz = Class.forName("com.mysql.jdbc.Driver");
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

    //方式四: 可以只是加载驱动, 不用显示的注册驱动过了
    @Test
    public void testConnection() throws Exception {
        //1. 提供另外三个连接的基本信息
        String url = "jdbc:mysql://localhost:3306/test?characterEncoding=latin1";
        String user = "root";
        String password = "root";

        //2. 获取Driver实现类的对象
        Class.forName("com.mysql.jdbc.Driver");
        //相较于方式3, 可以省略如下的操作:
//        Driver driver = (Driver) clazz.newInstance();
//        //注册驱动
//        DriverManager.registerDriver(driver);

        //为什么可以胜率上述操作呢?
        //在mysql的Driver实现类中, 声明了static代码块在生成式注册

        //3. 获取连接
        DriverManager.getConnection(url, user, password);
    }

    //方式五(final版): 将数据库连接需要的4个基本信息声明在配置文件中, 通过读取配置文件的方式, 获取连接
    /*
    此种方法的好处?
    1. 实现了数据与代码的分离, 实现了解耦
    2. 减少了配置信息的暴露
    3. 提高了可重用性和可移植性
    4. 如果需要修改配置文件信息, 可以避免程序重新打包
    */
    @Test
    public void getConnection5() throws Exception {
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
    }
}
