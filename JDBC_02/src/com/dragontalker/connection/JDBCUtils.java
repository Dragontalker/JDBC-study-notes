package com.dragontalker.connection;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class JDBCUtils {

    public static Connection getConnection1() throws SQLException {
        ComboPooledDataSource cpds = new ComboPooledDataSource("hello-c3p0");
        return cpds.getConnection();
    }
}
