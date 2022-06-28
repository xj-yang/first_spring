package com.atguigu.service;

import com.atguigu.pojo.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 事务的传播特性测试
 * @author yjq
 * @create 2022-05-24-16:59
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application.xml")
public class TxPropagationTest {

    @Autowired
    private TxPropagation txPropagation;

    @Test
    public void test01(){
        Book book = new Book();
        book.setName("活着");
        book.setPrice(100);
        book.setCount(100);
        txPropagation.insertBook(book);
    }

    @Test
    public void test02(){
        txPropagation.testREQUIRED01();
    }
    @Test
    public void test03(){
        txPropagation.testREQUIRED02();
    }
    @Test
    public void test04(){
        txPropagation.testREQUIRED03();
    }


}
