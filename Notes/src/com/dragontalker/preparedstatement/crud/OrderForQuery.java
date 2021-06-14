package com.dragontalker.preparedstatement.crud;

import com.dragontalker.util.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;

/**
 * @Description 针对orders表的查询操作
 */

public class OrderForQuery {

    @Test
    public void testQuery1() throws Exception{
        Connection conn = JDBCUtils.getConnection();
    }
}
