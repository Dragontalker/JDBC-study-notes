package com.dragontalker.transaction;

import com.dragontalker.utils.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
1. 什么叫数据库事务?
    - 事务: 一组逻辑单元, 使数据从一种状态变换到另一种状态
        > 一组逻辑单元: 一个或多个DML操作

2. 事务处理的原则:
    - 保证所有事务都作为一个工作单元来执行, 即使出现故障, 都不能改变执行逻辑
    - 当在一个事务中执行多个操作时,
        - 要么所有的事务都被提交(commit), 那么这些修改就被永久保存下来
        - 要么数据库管理系统将放弃所作的所有修改, 整个事务回滚(rollback)到最初状态

3. 数据一旦提交, 就不可回滚

4. 哪些操作会导致数据的自动提交?
    > DDL操作一旦执行, 都会自动提交
        > set autocommit = false对DDL无效
    > DML操作默认情况下, 一旦执行, 就会自动提交
        > 我们可以通过set autocommit = false的方式取消DML操作的自动提交
    > 默认在关闭连接时, 会自动的提交数据

 */
public class TransactionTest {

    //****************************未考虑数据库事务的转账操作****************************
    /*
    针对于数据表user_table来说:
    - AA用户给BB用户转账100
    - update user_table set balance = balance - 100 where user = 'AA';
    - update user_table set balance = balance + 100 where user = 'BB';
     */
    @Test
    public void testUpdate(){
        String sql1 = "update user_table set balance = balance - 100 where user = ?";
        update(sql1, "AA");

        //模拟网路异常
        //System.out.println(10 / 0);

        String sql2 = "update user_table set balance = balance + 100 where user = ?";
        update(sql2, "BB");

        System.out.println("转账成功");
    }

    //通用的增删改操作 --- version 1.0
    public int update(String sql, Object ... args) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, ps);
        }
        return 0;
    }

    //****************************考虑数据库事务的转账操作****************************

    @Test
    public void testUpdateWithTx() {
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            System.out.println(conn.getAutoCommit());

            //1. 取消数据的自动提交
            conn.setAutoCommit(false);


            String sql1 = "update user_table set balance = balance - 100 where user = ?";
            update(conn, sql1, "AA");

            //模拟网路异常
            //System.out.println(10 / 0);

            String sql2 = "update user_table set balance = balance + 100 where user = ?";
            update(conn, sql2, "BB");

            System.out.println("转账成功");

            //2. 提交数据
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();

            //3. 回滚数据
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
    }

    //通用的增删改操作 --- version 2.0
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