package com.atguigu.test;

import com.atguigu.ioc.pojo.Person;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 使用spring的单元测试方法：
 *      可以省去容器的创建，和组件的获取。
 *      组件可以直接通过注解注入得到。
 *
 *      @ContextConfiguration(locations = "classpath:application.xml"):指定spring配置文件的位置
 *      @RunWith(): 指定使用哪个测试驱动
 *          @RunWith(SpringJUnit4ClassRunner.class)： 表示指定测试驱动为spring- test 提供的。
 *          以前的@Test注解是junit提供的，现在执行的@Test是spring提供的
 * @author yjq
 * @create 2022-05-21-9:55
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application.xml")
public class MyTest {

    @Autowired
    private Person person;

   @Test
    public void test01(){
        System.out.println(person);
    }
}
