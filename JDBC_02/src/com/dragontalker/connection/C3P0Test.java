package com.dragontalker.connection;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;
import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class C3P0Test {

    //方式一:
    @Test
    public void testGetConnection() throws Exception {
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");
        Properties props = new Properties();
        props.load(is);
        String url = props.getProperty("url");
        String user = props.getProperty("user");
        String password = props.getProperty("password");
        String driverClass = props.getProperty("driverClass");

        //获取C3P0数据库连接池
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        cpds.setDriverClass(driverClass); //loads the jdbc driver
        cpds.setJdbcUrl(url);
        cpds.setUser(user);
        cpds.setPassword(password);

        //通过设置相关的参数, 对数据库连接池进行管理
        //设置初始时数据库连接池中的连接数
        cpds.setInitialPoolSize(10);

        Connection connection = cpds.getConnection();
        System.out.println(connection);

        //销毁数据库连接池
        DataSources.destroy(cpds);
    }

    //方式二: 使用配置文件
    @Test
    public void testGetConnection1() throws SQLException {
        ComboPooledDataSource cpds = new ComboPooledDataSource("hello-c3p0");
        Connection connection = cpds.getConnection();
        System.out.println(connection);
    }
}
