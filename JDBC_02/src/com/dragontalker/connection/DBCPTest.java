package com.dragontalker.connection;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DBCPTest {

    /**
     * @Description 测试DBCP的数据库连接池技术
     */
    //方式一: 不推荐
    @Test
    public void testGetConnection() throws SQLException {
        //创建了DBCP的数据库连接池
        BasicDataSource source = new BasicDataSource();

        //设置基本信息
        source.setDriverClassName("com.mysql.jdbc.Driver");
        source.setUrl("jdbc:mysql://localhost:3306/test?characterEncoding=latin1");
        source.setUsername("root");
        source.setPassword("password");

        //设置数据库管理相关的属性
        source.setInitialSize(10);
        source.setMaxActive(100);

        Connection conn = source.getConnection();
        System.out.println(conn);
    }

    //方式二: 使用配置文件
    @Test
    public void testGetConnection1() throws Exception{
        Properties props = new Properties();
        //方式1:
        //InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("dbcp.properties");

        //方式2:
        FileInputStream is = new FileInputStream(new File("src\\dbcp.properties"));

        props.load(is);
        DataSource source = BasicDataSourceFactory.createDataSource(props);
        Connection conn = source.getConnection();
        System.out.println(conn);
    }
}
