package com.atguigu.ioc;

import com.atguigu.ioc.pojo.Person;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.Field;

/**
 * 注意：BeanPostProcessor的两个方法一个是在调用bean的初始化方法前调用，一个是在调用bean的初始化方法后调用
 *      并不是在bean初始化。
 *      往往这个时候bean已经完成了初始化操作。
 * @author yjq
 * @create 2022-05-20-20:10
 */
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("在bean调用初始化方法前调用，并且可以对bean对象进行处理，然后返回bean对象");
        Person person = (Person) bean;
        System.out.println(person.getName());
        System.out.println(person.getAge());
        System.out.println("bean对象名为："+beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("在bean调用初始化方法后调用，并且可以对bean对象进行处理，然后返回bean对象");
        Person person = (Person) bean;
        System.out.println(person.getName());
        System.out.println(person.getAge());
        System.out.println("bean对象名为："+beanName);
        return bean;
    }
}
