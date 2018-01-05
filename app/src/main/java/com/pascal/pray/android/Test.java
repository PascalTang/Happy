package com.pascal.pray.android;

import java.util.List;

/**
 * Created by Pascal on 2018/1/6.
 */

public class Test {

    /**
     * number : 1020501
     * name : 小傑
     * age : 32
     * sex : M
     * interest : ["網頁設計","撰寫文章"]
     */

    private String number;
    private String name;
    private int age;
    private String sex;
    private List<String> interest;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public List<String> getInterest() {
        return interest;
    }

    public void setInterest(List<String> interest) {
        this.interest = interest;
    }
}
