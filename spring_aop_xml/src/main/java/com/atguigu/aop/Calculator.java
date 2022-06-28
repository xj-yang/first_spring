package com.atguigu.aop;

import org.springframework.stereotype.Component;

/**
 * @author yjq
 * @create 2022-05-21-13:12
 */
@Component
public class Calculator {


    public int add(int a, int b) {
        System.out.println("add方法执行了。。。");
        return a+b;
    }


    public int sub(int a, int b) {
        System.out.println("sub方法执行了。。。");
        return a-b;
    }

    public int div(int a, int b) {
        System.out.println("div方法执行了。。。");
        return a/b;
    }

}
