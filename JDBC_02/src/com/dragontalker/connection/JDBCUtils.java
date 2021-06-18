package com.dragontalker.connection;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class JDBCUtils {

    //数据库连接池只需提供一个即可
    private static ComboPooledDataSource cpds = new ComboPooledDataSource("hello-c3p0");

    public static Connection getConnection1() throws SQLException {
        return cpds.getConnection();
    }
}
