package com.dragontalker.dao;

import com.dragontalker.utils.JDBCUtils;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
封装了对于数据表的通用的操作
 */
public class BaseDAO {

    public int update(Connection conn, String sql, Object ... args) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(null, ps);
        }
        return 0;
    }

    public <T> T getInstance(Connection conn, Class<T> clazz, String sql, Object ... args) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
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
            JDBCUtils.closeResource(null, ps, rs);
        }

        return null;
    }

    public <T> List<T> getForList(Connection conn, Class<T> clazz, String sql, Object ... args) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            for(int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }

            //执行结果集
            rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            //获取列数
            int columnCount = rsmd.getColumnCount();
            //创建集合对象
            ArrayList<T> list = new ArrayList<>();
            while(rs.next()){
                T t = clazz.newInstance();
                //处理结果集一行数据中的每一个列: 给t对象指定的属性赋值
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

                list.add(t);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(null, ps, rs);
        }

        return null;
    }

    public <E> E getValue(Connection conn, String sql, Object ... args) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }

            rs = ps.executeQuery();
            if(rs.next()) {
                return (E) rs.getObject(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtils.closeResource(null, ps, rs);
        }
        return null;
    }
}
