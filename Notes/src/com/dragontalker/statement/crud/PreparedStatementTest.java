package com.dragontalker.statement.crud;

import com.dragontalker.bean.Customer;
import com.dragontalker.bean.Order;
import com.dragontalker.util.JDBCUtils;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Scanner;

/**
 * @Description 演示使用PreparedStatement替换Statement, 解决sql注入问题
 */
public class PreparedStatementTest {

    @Test
    public void testLogin() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入用户名: ");
        String user = scanner.nextLine();
        System.out.println("请输入密码: ");
        String password = scanner.nextLine();

        String sql = "SELECT user, password FROM user_table WHERE user = '1' or ' AND password = '=1 or '1'";
        User returnUser = getInstance(User.class, sql);
        if(returnUser != null) {
            System.out.println("登陆成功");
        } else {
            System.out.println("登录失败");
        }
    }

    /**
     * @Description 针对于不同的表的通用的查询操作
     * @param clazz
     * @param sql
     * @param args
     * @param <T>
     * @return
     */
    public <T> T getInstance(Class<T> clazz, String sql, Object ... args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(sql);
            for(int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }

            //执行结果集
            rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            //获取列数
            int columnCount = rsmd.getColumnCount();
            if(rs.next()){
                T t = clazz.newInstance();
                for(int i = 0; i < columnCount; i++) {
                    //获取每个列的列值: 通过ResultSet
                    Object columnValue = rs.getObject(i + 1);
                    //获取每个列的列名: 通过ResultSetMetaData
                    //获取列的别名: getColumnLabel()
                    String columnLabel = rsmd.getColumnLabel(i + 1);

                    //通过反射, 将对象指定名的ColumnName的属性赋值为指定的值ColumnValue
                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t, columnValue);
                }

                return t;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, ps, rs);
        }

        return null;
    }
}
