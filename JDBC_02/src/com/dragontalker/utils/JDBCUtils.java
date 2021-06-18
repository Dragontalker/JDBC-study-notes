package com.dragontalker.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCUtils {

    public static Connection getConnection() throws Exception{
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");
        Properties props = new Properties();
        props.load(is);
        String url = props.getProperty("url");
        String user = props.getProperty("user");
        String password = props.getProperty("password");
        String driverClass = props.getProperty("driverClass");
        Class.forName(driverClass);
        return DriverManager.getConnection(url, user, password);
    }

    public static void closeResource(Connection conn, Statement ps) {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
