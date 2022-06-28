package com.atguigu.aspectj;

import com.atguigu.aspectj.Calculate;
import org.springframework.stereotype.Component;

/**
 * @author yjq
 * @create 2022-05-21-13:12
 */
@Component
public class Calculator{


    public int add(int a, int b) {
        System.out.println("方法执行了。。。");
        return a+b;
    }


    public int sub(int a, int b) {
        System.out.println("方法执行了。。。");
        return a-b;
    }
}
