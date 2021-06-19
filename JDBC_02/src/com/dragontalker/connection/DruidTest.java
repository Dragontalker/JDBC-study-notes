package com.dragontalker.connection;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

public class DruidTest {

    @Test
    public void getConnection() throws Exception{
        Properties props = new Properties();
        FileInputStream is = new FileInputStream(new File("src\\dbcp.properties"));        props.load(is);
        DataSource source = DruidDataSourceFactory.createDataSource(props);
        Connection conn = source.getConnection();
        System.out.println(conn);
    }
}
