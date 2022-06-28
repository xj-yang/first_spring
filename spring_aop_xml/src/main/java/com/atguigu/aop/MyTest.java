package com.atguigu.aop;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author yjq
 * @create 2022-05-23-14:11
 */
public class MyTest {


    @Test
    public void test01(){
        ApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:application.xml");
        Calculator calculator = context.getBean(Calculator.class);
        System.out.println(calculator.getClass());
        int add = calculator.div(1, 9);
    }
}
