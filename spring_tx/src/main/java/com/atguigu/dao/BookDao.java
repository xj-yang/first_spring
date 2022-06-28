package com.atguigu.dao;

import com.atguigu.pojo.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author yjq
 * @create 2022-05-23-17:31
 */

@Repository
public class BookDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    //获取书的价格
    public float getPrice(String name){
        String sql = "select b_price from books where b_name = ? ";
        Float bookPrice = jdbcTemplate.queryForObject(sql, Float.class, name);

        return bookPrice;
    }

    //修改数的库存
    @Transactional
    public void modifyCount(int i,String name){
        String sql = "update books set b_count = b_count - ? where b_name = ?";
        int i1 = jdbcTemplate.update(sql, i, name);
        System.out.println("修改数量成功");
    }

    //添加书籍
    @Transactional
    public void insert(Book book){
        String sql = "insert into books(b_name,b_price,b_count) values(?,?,?)";
        int i2 = jdbcTemplate.update(sql, book.getName(), book.getPrice(), book.getCount());
        System.out.println("修改书籍成功");
    }


}
