package com.example.beanvalidation.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

/**
 * Created by sai.luo on 2017-9-18.
 */

public class User {
    @Length(min = 1,max = 10,message = "名称长度不能超过10个字符")
    private String name ;
    @Range(min = 0,max = 100,message = "年龄在0到100之间")
    private int age ;
    @Range(min = 1,message = "id 要大于0")
    private int id ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", id=" + id +
                '}';
    }
}
