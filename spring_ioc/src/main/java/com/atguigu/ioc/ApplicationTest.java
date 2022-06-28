package com.atguigu.ioc;

import com.atguigu.ioc.pojo.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author yjq
 * @create 2022-05-20-17:49
 */
public class ApplicationTest {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
        Person person = (Person) context.getBean("person");



    }
}
