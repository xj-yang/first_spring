package com.atguigu.service;

import com.atguigu.dao.AccountDao;
import com.atguigu.dao.BookDao;
import com.atguigu.pojo.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 事务的传播特性
 * @author yjq
 * @create 2022-05-24-17:00
 */
@Service
public class TxPropagation {

    @Autowired
    private BookDao bookDao;

    @Autowired
    private AccountDao accountDao;

    public void insertBook(Book book){
        bookDao.insert(book);
    }


    /**
     * 当前方法没事务运行,调用一个事务方法，查看事务的传播特性
     *      subMoney():方法的传播特性为REQUIRED
     * 结果是：testREQUIRED01()原本为没有是事务的方法，因为调用了subMoney()，也创建了事务。
     *         所以当addCount()方法执行错误时，整个testREQUIRED01()方法都回滚了。
     */
    public void testREQUIRED01(){

        float price = bookDao.getPrice("活着");

        accountDao.addCount(1, "tom");

        accountDao.subMoney(price, "tom");
    }

    /**
     * testREQUIRED02()的事务没有回滚，subMoney（）修改money成功。
     */
    @Transactional(noRollbackFor = {ArithmeticException.class})
    public void testREQUIRED02(){
        float price = bookDao.getPrice("活着");

        accountDao.subMoney(price, "tom");
        accountDao.addCount(1, "tom");

    }

    /**
     * 设置subMoney()的事务隔离级别为@Transactional(propagation = Propagation.REQUIRES_NEW)，
     * 那么当主方法因为调用其他方法发生异常回滚，那么subMoney()会因为创建了一个新的事务并且运行
     * 在里面，所以不会被主方法原有的事务影响，所以不会回滚。
     */
    @Transactional()
    public void testREQUIRED03(){
        float price = bookDao.getPrice("活着");

        accountDao.subMoney(price, "tom");
        accountDao.addCount(1, "tom");

    }
}
