package com.atguigu.service;

import com.atguigu.dao.AccountDao;
import com.atguigu.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;

/**
 * @author yjq
 * @create 2022-05-25-10:36
 */
public class TransactionDemo02 {

    @Autowired
    private BookDao bookDao;
    @Autowired
    private AccountDao accountDao;

    public void buyBook(){
        String bookName = "水浒传";
        float price = getprice(bookName);
        //扣除用户的钱
        accountDao.subMoney(price,"tom");


        //用户的书本数量加一
        accountDao.addCount(1, "tom");
        //书店的书本数量减一
        bookDao.modifyCount(1, bookName);
    }



    /**
     * 一个用户购买书籍的具体操作的集合
     */

    public void buyBook02(){
        String bookName = "水浒传";
        float price = getprice(bookName);
        //扣除用户的钱
        accountDao.subMoney(price,"tom");
        //用户的书本数量加一
        accountDao.addCount(1, "tom");
        //书店的书本数量减一
        bookDao.modifyCount(1, bookName);
    }


    //获取书本的价格
    public float getprice(String name){
        float price = bookDao.getPrice(name);
        System.out.println(price);
        return price;
    }
}
