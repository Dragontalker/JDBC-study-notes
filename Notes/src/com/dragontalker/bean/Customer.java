package com.dragontalker.bean;

import java.sql.Date;

/**
 * ORM编程思想(Object Relational Mapping)
 * 一个数据表对应一个Java类
 * 表中的一条记录对应Java类的一个对象
 * 表中的一个字段对应Java类的一个属性
 */

public class Customer {
    private int id;
    private String name;
    private String email;
    private Date birth;

    public Customer() {}

    public Customer(int id, String name, String email, Date birth) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birth = birth;
    }


}
