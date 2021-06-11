package com.dragontalker.preparedstatement.crud;

import com.dragontalker.connection.ConnectionTest;
import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.Properties;

/**
 * 使用PreparedStatement来替换Statement, 实现对数据表的增删改查操作
 *
 * 增删改(广义上的修改): 没有return
 * 查: 有return
 */

public class PreparedStatementUpdateTest {

    //向customers表中添加一条记录
    @Test
    public void testInsert() throws Exception{
        //1. 读取配置文件中的4个基本信息
        InputStream is = ConnectionTest.class.getClassLoader().getResourceAsStream("jdbc.properties");

        Properties props = new Properties();
        props.load(is);

        String url = props.getProperty("url");
        String user = props.getProperty("user");
        String password = props.getProperty("password");
        String driverClass = props.getProperty("driverClass");

        //2. 加载驱动
        Class.forName(driverClass);

        //3. 获取连接
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println(connection);

        //4. 预编译sql语句, 返回PrepareStatement的实例
        String sql = "insert into customers(name, email, birth) values(?,?,?)";
        PreparedStatement ps = connection.prepareStatement(sql);

        //5. 填充占位符
        ps.setString(1, "NeZha");
        ps.setString(2, "nazha@gmail.com");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = sdf.parse("1000-01-01");
        ps.setDate(3, new Date(date.getTime()));

        //6. 执行sql操作
        ps.execute();

        //7. 资源的关闭
        ps.close();
        connection.close();
    }
}
