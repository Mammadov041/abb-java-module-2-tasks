package org.abb_tech.module1lesson9.classes;

import java.util.List;

public class User {
    private String name;
    private int age;
    private List<BorrowRecord> borrowHistory;

    public User(String name,int age,List<BorrowRecord> borrowHistory){
        setName(name);
        setAge(age);
        setBorrowHistory(borrowHistory);
    }

    @Override
    public String toString() {
        return String.format("===== USER INFO =====\n===== NAME:%s =====\n===== AGE:%d =====\n",this.name,this.age);
    }

    // setters
    public void setName(String name){
        this.name = name;
    }

    public void setBorrowHistory(List<BorrowRecord> borrowHistory){
        this.borrowHistory = borrowHistory;
    }

    private void setAge(int age) {
        this.age = age;
    }

    // getters
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public List<BorrowRecord> getBorrowHistory() {
        return borrowHistory;
    }
}