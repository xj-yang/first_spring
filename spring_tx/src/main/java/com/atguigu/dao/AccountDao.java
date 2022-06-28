package com.atguigu.dao;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author yjq
 * @create 2022-05-23-17:41
 */

@Repository
public class AccountDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private DruidDataSource dataSource;
    private DruidPooledConnection connection = null;


    public DruidPooledConnection getConnect() throws SQLException {
        connection = dataSource.getConnection();
        return connection;
    }

    public void closeConnect() throws SQLException {
        if(connection!=null){
            connection.close();
        }
    }

    //增加用户的书的数目
    //@Transactional
    public void addCount(int i,String name){
        int a =10/0;
        String sql ="update account set book_count =book_count + ? where acc_name = ?";
        int i1 = jdbcTemplate.update(sql, i, name);
        System.out.println("修改用户"+name+"的书的数目成功！");
    }
    //扣除用户的账户的余额
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void subMoney(float f,String name){
        String sql = "update account set acc_money =acc_money - ? where acc_name = ?";
        int i2 = jdbcTemplate.update(sql, f, name);
        System.out.println("扣除用户"+name+"的账户的余额成功！");
    }
}
