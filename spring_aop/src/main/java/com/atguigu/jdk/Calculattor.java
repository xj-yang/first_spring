package com.atguigu.jdk;

/**
 * @author yjq
 * @create 2022-05-21-13:12
 */
public class Calculattor implements Calculate{

    @Override
    public int add(int a, int b) {
        return a+b;
    }

    @Override
    public int sub(int a, int b) {
        return a-b;
    }
}
