package com.dragontalker.connection;

import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DBCPTest {

    /**
     * @Description 测试DBCP的数据库连接池技术
     */
    public void testGetConnection() throws SQLException {
        //创建了DBCP的数据库连接池
        DataSource source = new BasicDataSource();

        Connection conn = source.getConnection();
    }
}
