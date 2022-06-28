package com.atguigu;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author yjq
 * @create 2022-05-20-13:36
 */
public class MyTest {

    @Test
    public void test01(){
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("application.xml");
        System.out.println(beanFactory.getBean("person"));
        System.out.println(beanFactory.getBean("person2"));
        System.out.println(beanFactory.getBean("person3"));

        System.out.println(beanFactory.getBean("person4"));

        System.out.println(beanFactory.getBean("person5"));

        System.out.println(beanFactory.getBean("person6"));

//        System.out.println(beanFactory.getBean("aclass"));
//        System.out.println(beanFactory.getBean("bclass"));

        System.out.println(beanFactory.getBean("cclass"));
        System.out.println(beanFactory.getBean("dclass"));

        System.out.println("-----------------自动装配------------------");
        System.out.println(beanFactory.getBean("fclass")); //ConversionNotSupportedException

    }
}
