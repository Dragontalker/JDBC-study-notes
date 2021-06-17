package com.dragontalker.preparedstatement.crud;

import com.dragontalker.bean.Order;
import com.dragontalker.util.JDBCUtils;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.*;

/**
 * @Description 针对orders表的更删改查询操作
 */

public class OrderForQuery {

    /**
     * 针对于表的字段名于类的属性名不相同的情况:
     * 1. 必须声明sql时, 使用类的属性名来命名字段的别名
     * 2. 使用ResultSetMetaData时, 需要使用getColumnLabel()来替换getColumnName()来获取列的别名
     *  补充说明: 如果sql中没有给字段起别名, getColumnLabel()获取的就是列名
     */
    @Test
    public void testOrderForQuery() {
        String sql = "select order_id orderId, order_name orderName, order_date orderDate from `orders` where order_id = ?";
        Order order = orderForQuery(sql, 1);
        System.out.println(order);
    }

    /**
     * 通用的针对于order表的查询操作
     * @return
     */
    public Order orderForQuery(String sql, Object ... args){
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
                Order order = new Order();
                for(int i = 0; i < columnCount; i++) {
                    //获取每个列的列值: 通过ResultSet
                    Object columnValue = rs.getObject(i + 1);
                    //获取每个列的列名: 通过ResultSetMetaData
                    //获取列的别名: getColumnLabel()
                    String columnLabel = rsmd.getColumnLabel(i + 1);

                    //通过反射, 将对象指定名的ColumnName的属性赋值为指定的值ColumnValue
                    Field field = Order.class.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(order, columnValue);
                }

                return order;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, ps, rs);
        }

        return null;
    }

    @Test
    public void testQuery1() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "select order_id, order_name, order_date from `orders` where order_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setObject(1, 1);

            rs = ps.executeQuery();
            if(rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                Date date = rs.getDate(3);

                Order order = new Order(id, name, date);
                System.out.println(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, ps, rs);
        }
    }
}
