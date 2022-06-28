package com.atguigu.service;

import com.atguigu.dao.AccountDao;
import com.atguigu.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;

/**
 * @author yjq
 * @create 2022-05-24-14:01
 */
@Service
public class TransactionDemo01 {

    @Autowired
    private BookDao bookDao;
    @Autowired
    private AccountDao accountDao;


    /**
     * @Transactional事务细节：
     *      isolation--Isolation: 事务的隔离级别
     *              DEFAULT: 指的是使用数据库默认的隔离级别；如mysql默认的隔离级别就是 REPEATABLE_READ
     *              READ_UNCOMMITTED: 读未提交  （不能解决任何问题）
     *              READ_COMMITTED: 读已提交    （可以防止脏读的问题）
     *              REPEATABLE_READ: 可重复读   （可以防止脏读和不可重复读的问题）
     *              SERIALIZABLE:  串行化       （可以防止脏读，不可重复读和幻读的问题）
     *
     *      数据库并发操作存在的几种问题：
     *              脏读：指的是一个事务读取了另一个未提交事务修改的数据，另一个事务进行事物回滚时。
     *                   这个事务读取的数据就是一个不存在的脏数据。
     *              不可重复读：指的是一个事务读取了另一个已提交事务修改的数据，导致每一次读取的数据
     *                   都是另一个事务提交过后的数据，导致每次读取的数据都是不一样的。
     *              幻读：指的是一个事务读取了另一个已提交事务的插入的数据，导致每次读取的数据数目都不一致。
     *      --------------------------------------------------
     *
     *      propagation--Propagation: 事务的传播特性
     *              什么是事务的传播特性？
     *                  指的是当一个方法调用另一个事务方法时（被传入的就是另一个事务），在这种嵌套的事务方法调用中事务的传播是如何做的。
     *              spring提供了这么几种的事务传播特性：
     *                  REQUIRED:   默认的，指的是如果当前有事务运行，那么另一个事务方法就运行在当前事务内；否者就创建一个新的事务。
     *                  REQUIRES_NEW:   指的是创建一个新的事务运行，如果当前有事务则挂起。
     *                  SUPPORTS:   指的是如果当前有事务就运行在当前事务，如果没有就以非事务方法运行。
     *                  NOT_SUPPORTED: 指的是以非事务方法运行，如果当前有事务则把事务挂起。
     *                  NEVER: 不支持事务，如果当前有事务就抛出异常
     *                  MANDATORY:  支持事务，如果当前没有事务就抛出异常
     *                  NESTED:   如果当前存在事务，则在嵌套事务内执行。如果当前没有事务，则进行与REQUIRED类似的操作
     *
     *      --------------------------------------------------
     *
     *      noRollbackFor-- Class<? extends Throwable>[]: 哪些异常不回滚（填的是类型） 常用
     *      noRollbackForClassName--String[]: 哪些异常不回滚（填的是类型）
     *      rollbackFor--Class<? extends Throwable>[]:  哪些异常需要回滚（填的是类型） 常用
     *      rollbackForClassName--String[]: 哪些异常需要回滚（填的是类型）
     *      异常的分类：
     *          编译异常： 要么try-catch，要么throws；默认是不会滚的。
     *          运行时异常：可以不用处理，默认就是回滚的。
     *      上述属性值解决的就是：
     *          让运行时异常默认回滚的可以指定某个运行时异常不回滚；
     *          让编译异常默认不会滚的可以指定某个编译异常回滚。
     *      --------------------------------------------------
     *
     *      readOnly--boolean:  设置事务为只读,默认为false
     *              可以进行事务优化：提高事务代码的执行效率
     *              原因：如果当前的事务中的操作都是对数据库的读操作，那么我们可以把事务设置为只读属性。这样就可以不管事务的一推操作。
     *              readOnly = true
     *      timeout--int:（秒为单位） 设置事务的超时时间，事务超出指定时长之后自动终止并且回滚
     *              如果事务超时会报异常 TransactionTimedOutException；
     */
    @Transactional(rollbackFor = {FileNotFoundException.class},
            noRollbackFor = {ArithmeticException.class},
            isolation = Isolation.SERIALIZABLE,
            propagation = Propagation.NEVER)
    public void buyBook() throws FileNotFoundException {
        String bookName = "水浒传";
        float price = getprice(bookName);
        //扣除用户的钱
        accountDao.subMoney(price,"tom");

//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        //添加一个运行时异常，事务默认是回滚的
        int i = 10/0; //ArithmeticException
        //添加一个编译异常,文件不存在的；事务默认是不回滚的
        //new FileInputStream("D://aaaaaa.txt"); //FileNotFoundException


        //用户的书本数量加一
        accountDao.addCount(1, "tom");
        //书店的书本数量减一
        bookDao.modifyCount(1, bookName);
    }



    /**
     * 一个用户购买书籍的具体操作的集合
     */
    @Transactional  //为该方法
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
