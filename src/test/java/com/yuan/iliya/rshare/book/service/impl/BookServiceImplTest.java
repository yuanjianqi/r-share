package com.yuan.iliya.rshare.book.service.impl;

import com.yuan.iliya.rshare.book.entity.po.Book;
import com.yuan.iliya.rshare.book.service.BookService;
import com.yuan.iliya.rshare.core.constant.BookType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * All Rights Reserved, Designed By Iliya Kaslana
 * Copyright ©2018
 *
 * @author Iliya Kaslana
 * @version 1.0
 * @date 2018/9/17 14:23
 * @since 1.8
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class BookServiceImplTest {

    @Autowired
    private BookService bookService;
    @Test
    public void save() {




        for (int i = 0; i < 40; i++) {
            Book book = new Book();
            book.setName("旁白聚聚奋斗的一生" + i);
            book.setPublishTime(new Date());
            book.setPress("重庆大学出版社");
            book.setRemainNumber(10 + i);
            book.setOldPrice(40.24 + i);
            book.setClassify(BookType.LIFE_AND_LEISURE);
            book.setDetail("一代大师旁白聚聚登顶之作");
            book.setOld((8 + i) % 10);

        }
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void findBookById() {
        Book bookById = bookService.findBookById("4028e48165e636af0165e636b6380000");
        System.out.println(bookById);
    }

    @Test
    public void findBooks() {
        List<Book> books = bookService.findBooks();
        System.out.println(books);
    }
}