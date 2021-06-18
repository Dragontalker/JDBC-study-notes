package com.dragontalker.exer;

import com.dragontalker.util.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

//课后练习2
public class Exercise2Test {

    //问题1: 向ExamStudent表添加一条记录
    @Test
    public void testInsert() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("四级/六级:");
        int type = scanner.nextInt();

        System.out.println("身份证号:");
        String IDCard = scanner.next();

        System.out.println("准考证号:");
        String examCard = scanner.next();

        System.out.println("学生姓名:");
        String studentName = scanner.next();

        System.out.println("所在城市:");
        String location = scanner.next();

        System.out.println("考试成绩:");
        String grade = scanner.next();

        String sql = "insert into `examstudent` (type, IDCard, examCard, studentName, location, grade) values (?, ?, ?, ?, ?, ?)";
        int insertCount = update(sql, type, IDCard, examCard, studentName, location, grade);
        if (insertCount > 0) {
            System.out.println("添加成功");
        } else {
            System.out.println("添加失败");
        }
    }

    //通用的增删改操作
    public int update(String sql, Object ... args) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            /*
            ps.execute():
            - 如果执行的查询操作, 有返回结果, 则此方法返回true;
            - 如果执行的是增、删、改、操作, 没有返回结果, 则此方法返回false;
             */
            //方式一:
            //return ps.execute();
            //方式二:
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, ps);
        }
        return 0;
    }
}
