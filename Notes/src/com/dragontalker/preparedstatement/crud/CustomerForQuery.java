package com.dragontalker.preparedstatement.crud;

import com.dragontalker.bean.Customer;
import com.dragontalker.util.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 针对于Customers表的查询操作
 */

public class CustomerForQuery {

    /**
     * @Description 针对customers表的通用的查询操作
     */
    public void queryForCustomers() throws Exception{
        Connection conn = JDBCUtils.getConnection();
    }

    @Test
    public void testQuery1() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "select id, name, email, birth from customers where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setObject(1, 1);

            //1. 执行并返回结果集
            resultSet = ps.executeQuery();

            //2. 处理结果集
            if(resultSet.next()) {//判断结果集的下一条是否有数据, 如果有数据, 返回true并指针下移, 如果false直接结束

                //3. 获取当前数据各个字段值
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String email = resultSet.getString(3);
                Date birth = resultSet.getDate(4);

                //4. 将数据封装成一个对象
                Customer customer = new Customer(id, name, email, birth);
                System.out.println(customer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //5. 关闭资源
            JDBCUtils.closeResource(conn, ps, resultSet);
        }
    }
}
