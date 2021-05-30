package com.dragontalker.connection;

import org.junit.Test;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionTest {

    @Test
    public void test1() throws SQLException {

        Driver driver = new com.mysql.jdbc.Driver();

        //Tomcat: url:http://localhost:8080/gmall/keyboard.jpg
        String url = "jdbc:sql://localhost:3306/test";
        Properties info = null;

        //Connection conn = driver.connection(url, info);

        //System.out.println(conn);
    }

}
