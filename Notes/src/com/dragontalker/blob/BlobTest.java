package com.dragontalker.blob;

import com.dragontalker.util.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;

/**
 * @Description 测试使用PreparedStatement操作Blob类型的数据
 */

public class BlobTest {

    //向数据表customers中插入Blob类型的字段
    @Test
    public void testInsert() throws Exception{
        Connection conn = JDBCUtils.getConnection();
    }
}
