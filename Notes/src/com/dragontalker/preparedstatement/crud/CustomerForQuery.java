package com.dragontalker.preparedstatement.crud;

import com.dragontalker.util.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 针对于Customer表的查询操作
 */

public class CustomerForQuery {

    @Test
    public void testQuery1() {
        Connection conn = JDBCUtils.getConnection();
        String sql = "select id, name, email, birth from customers where id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);

        //执行并返回结果集
        ResultSet resultSet = ps.executeQuery();

        //处理结果集
        if(resultSet.next()) {//判断结果集的下一条是否有数据, 如果有数据, 返回true并指针下移, 如果false直接结束

            //获取当前数据各个字段值
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            String email = resultSet.getString(3);
            Date birth = resultSet.getDate(4);

        }
    }
}
