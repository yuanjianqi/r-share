package com.yuan.iliya.rshare.book.service;

import com.yuan.iliya.rshare.book.entity.dto.SearchBookDto;
import com.yuan.iliya.rshare.book.entity.po.Book;
import com.yuan.iliya.rshare.book.entity.vo.BookRecommendVo;

import java.io.Serializable;
import java.util.List;

/**
 * All Rights Reserved, Designed By Iliya Kaslana
 * Copyright ©2018
 *
 * @author Iliya Kaslana
 * @version 1.0
 * @date 2018/9/17 14:02
 * @since 1.8
 */
public interface BookService {

    /**
     * 向数据库中存储指定的书籍
     * @param book 书籍
     */
    public void save(Book book,String userId);

    /**
     * 向数据库中更新指定书籍
     * @param book 指定书籍
     */
    public void update(Book book);

    /**
     * 根据id来删除数据库中对应的书籍
     * @param id 书籍id
     */
    public void delete(Serializable id);

    /**
     * 根据id查询数据中对应的单个书籍
     * @param id 书籍id
     */
    public Book findBookById(Serializable id);

    /**
     * 查询数据库中的所有书籍
     * @return 所有书籍
     */
    public List<Book> findBooks();

    /**
     * 获得推荐的书单。
     * 要求返回六本书籍
     * 按最新的分享时间来
     * @return 首页的推荐书籍数据
     */
    public List<BookRecommendVo> findByPublishTime();

    /**
     *
     * 根据一定的条件查询书籍
     * 并做分页处理
     * @param dto 查询条件
     * @return 推荐的书籍
     */
    public List<BookRecommendVo> findBySearchBookDto(SearchBookDto dto);

    /**
     * 根据用户id查找它共享的书籍
     * @param userId 用户id
     * @return 用户共享的书籍
     */
    public List<BookRecommendVo> findBooksByUserId(String userId);
}
