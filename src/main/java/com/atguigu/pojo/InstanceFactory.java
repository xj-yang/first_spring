package com.atguigu.pojo;

/**
 * @author yjq
 * @create 2022-05-20-14:25
 */
public class InstanceFactory {

    public Person getPerson(){
        return new Person("tom",18);
    }
}
