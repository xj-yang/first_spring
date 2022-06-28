package com.atguigu.aspectj;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author yjq
 * @create 2022-05-21-13:53
 */
public class MyTest02 {

    @Test
    public void test01(){
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("classpath:application.xml");
        Calculator calculator = applicationContext.getBean(Calculator.class);
        System.out.println(calculator.getClass());
        System.out.println(calculator.add(1, 3));

    }
}
