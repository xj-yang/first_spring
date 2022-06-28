package com.atguigu.jdbc;

import com.atguigu.jdbc.pojo.Account;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yjq
 * @create 2022-05-23-15:40
 */
public class MyTest01 {


    /**
     * 批量插入
     */
    @Test
    public void test03(){
        ApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:application.xml");
        JdbcTemplate jdbcTemplate = context.getBean(JdbcTemplate.class);
        String sql = "insert into account(acc_name,acc_money) values(?,?)";

        List<Object[]> batchArgs = new ArrayList<>();
        batchArgs.add(new Object[]{"jack",9023.2});
        batchArgs.add(new Object[]{"miko",12903.2});
        batchArgs.add(new Object[]{"jery",9003.5});
        int[] ints = jdbcTemplate.batchUpdate(sql, batchArgs);
        System.out.println(ints.length);
    }

    /**
     * 更新操做
     */
    @Test
    public void test02(){
        ApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:application.xml");
        JdbcTemplate jdbcTemplate = context.getBean(JdbcTemplate.class);
        String sql = "update account set acc_name = ? where acc_id = ?";
        int i = jdbcTemplate.update(sql, "lucy", 2);
        System.out.println(i);
    }

    @Test
    public void test(){
        ApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:application.xml");

        JdbcTemplate jdbcTemplate = context.getBean(JdbcTemplate.class);
        System.out.println(jdbcTemplate);
        String sql= "select acc_id id,acc_name name,acc_money money from account";
        //查询没结果时会报错
        BeanPropertyRowMapper<Account> rowMapper = new BeanPropertyRowMapper<>(Account.class);
        List<Account> list = jdbcTemplate.query(sql, rowMapper);
        for (Account account : list) {
            System.out.println(account);
        }
    }
}
