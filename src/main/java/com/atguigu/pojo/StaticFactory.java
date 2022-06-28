package com.atguigu.pojo;

/**
 * @author yjq
 * @create 2022-05-20-14:17
 */
public class StaticFactory {

    public static Person getPerson(){

        return new Person();
    }
}
