package com.atguigu.aop;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

/**
 * @author yjq
 * @create 2022-05-23-15:12
 */
public class MyAfterReturn implements AfterReturningAdvice {

    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println("MyAfterReturn.....");
    }
}
