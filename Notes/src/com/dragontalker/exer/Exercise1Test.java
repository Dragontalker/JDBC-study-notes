package com.dragontalker.exer;

import com.dragontalker.util.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

//课后练习1
public class Exercise1Test {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入用户名");
        String name = scanner.nextLine();

        System.out.println("请输入邮箱");
        String email = scanner.nextLine();

        System.out.println("请输入生日");
        String birthday = scanner.nextLine(); //1992-09-08

        String sql = "insert into customers(name, email, birth) values (?, ?, ?)";
        Exercise1Test ex1 = new Exercise1Test();
        int insertCount = ex1.update(sql, name, email, birthday);
        if(insertCount > 0) {
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
