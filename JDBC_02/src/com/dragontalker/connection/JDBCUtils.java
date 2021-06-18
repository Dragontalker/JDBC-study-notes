package com.dragontalker.connection;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtils {

    //数据库连接池只需提供一个即可
    private static ComboPooledDataSource cpds = new ComboPooledDataSource("hello-c3p0");

    public static Connection getConnection1() throws SQLException {
        return cpds.getConnection();
    }

    /**
     * @Description 使用DBCP数据库连接池技术获取数据库连接
     * @return
     * @throws Exception
     */
    private static DataSource source;
    static {
        try {
            Properties props = new Properties();
            FileInputStream is = new FileInputStream(new File("src\\dbcp.properties"));
            props.load(is);
            source = BasicDataSourceFactory.createDataSource(props);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection2() throws Exception{
        return source.getConnection();
    }
}
