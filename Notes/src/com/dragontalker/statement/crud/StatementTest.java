package com.dragontalker.statement.crud;

import org.junit.Test;

import java.util.Scanner;

public class StatementTest {

    //使用Statement的弊端: 需要拼写sql语句, 并且存在SQL注入的问题
    @Test
    public void testLogin() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入用户名: ");
        String user = scanner.nextLine();
        System.out.println("请输入密码: ");
        String password = scanner.nextLine();
    }
}
