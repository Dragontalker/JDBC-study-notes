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
        BasicDataSource source = new BasicDataSource();

        //设置基本信息
        source.setDriverClassName("com.mysql.jdbc.Driver");
        source.setUrl("jdbc:mysql:///test");
        source.setUsername("root");
        source.setPassword("password");

        //设置数据库管理相关的属性
        source.setInitialSize(10);
        source.setMaxActive(100);

        Connection conn = source.getConnection();
    }
}
