package com.dragontalker.dao;

import com.dragontalker.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;

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
}
