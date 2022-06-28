package com.atguigu.pojo;

/**
 * @author yjq
 * @create 2022-05-23-17:28
 */
public class Account {

    private int id;
    private String name;
    private float money;
    private int bookCount;

    public Account() {
    }

    public Account(int id, String name, float money, int bookCount) {
        this.id = id;
        this.name = name;
        this.money = money;
        this.bookCount = bookCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public int getBookCount() {
        return bookCount;
    }

    public void setBookCount(int bookCount) {
        this.bookCount = bookCount;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", money=" + money +
                ", bookCount=" + bookCount +
                '}';
    }
}
