package com.dragontalker.connection;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class C3P0Test {

    @Test
    public void testGetConnection() throws Exception {
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");
        Properties props = new Properties();
        props.load(is);
        String url = props.getProperty("url");
        String user = props.getProperty("user");
        String password = props.getProperty("password");
        String driverClass = props.getProperty("driverClass");

        ComboPooledDataSource cpds = new ComboPooledDataSource();
        cpds.setDriverClass(driverClass); //loads the jdbc driver
        cpds.setJdbcUrl(url);
        cpds.setUser(user);
        cpds.setPassword(password);

        //设置初始时数据库连接池中的连接数
        cpds.setInitialPoolSize(10);
    }
}
