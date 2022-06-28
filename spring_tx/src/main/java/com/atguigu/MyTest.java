package com.atguigu;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.atguigu.dao.AccountDao;
import com.atguigu.dao.BookDao;
import com.atguigu.service.TransactionDemo01;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.FileNotFoundException;
import java.sql.SQLException;

/**
 * @author yjq
 * @create 2022-05-23-17:49
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application.xml")
public class MyTest {

    @Autowired
    private TransactionDemo01 transactionDemo01;

    @Autowired
    DruidDataSource dataSource;

    private DruidPooledConnection connection =null;
    @Autowired
    private BookDao bookDao;

    @Autowired
    private AccountDao accountDao;
    @Test
    public void test01(){
        float price = bookDao.getPrice("三国演义");
        System.out.println(price);
    }

    @Test
    public void test02(){
        bookDao.modifyCount(1, "三国演义");
    }

    @Test
    public void test03(){
        accountDao.subMoney(100, "tom");
    }

    @Test
    public void test04(){
        accountDao.addCount(1, "tom");
    }


    public DruidPooledConnection test05() throws SQLException {
        if(connection==null){
            return connection = dataSource.getConnection();
        }
        return connection;
    }
    @Test
    public void test06() throws SQLException {
        System.out.println(test05());
        System.out.println(test05());
    }

    @Test
    public void test07() throws FileNotFoundException {
        transactionDemo01.buyBook();
    }

}
