package com.dragontalker.blob;

/**
 * @Description 使用PreparedStatement实现批量数据的操作
 *
 * update, delete本身就具有批量操作的效果
 * 此时的批量操作, 主要指的是批量插入, 使用PreparedStatement如何实现更高效的批量插入?
 *
 * 题目: 要求向goods表中插入2000条数据
 *
 * CREATE TABLE goods(
 * 	id INT PRIMARY KEY AUTO_INCREMENT,
 *     NAME VARCHAR(25)
 * );
 *
 *
 * 方式一: 使用Statement
 * Connection conn = JDBCUtils.getConnection();
 * Statement st = conn.createStatement();
 * for (int i = 0; i <= 20000; i++) {
 *     String sql = "insert into goods(name) values('name_" + i + "')";
 *     st.execute(sql)
 * }
 */

public class InsertTest {
}
