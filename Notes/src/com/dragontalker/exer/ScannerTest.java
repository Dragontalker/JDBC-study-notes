package com.dragontalker.exer;

import java.util.Scanner;

public class ScannerTest {
    public static void main(String[] args) {
        System.out.println("Enter your username: ");
        Scanner scanner = new Scanner(System.in);
        String userName = scanner.nextLine();
        System.out.println("Your username is " + userName);
    }
}
