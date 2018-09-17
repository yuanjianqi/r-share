package com.yuan.iliya.rshare.book.dao;

import com.yuan.iliya.rshare.book.entity.dto.SearchBookDto;
import com.yuan.iliya.rshare.book.entity.po.Book;
import com.yuan.iliya.rshare.core.dao.BaseDao;

import java.util.List;

/**
 * All Rights Reserved, Designed By Iliya Kaslana
 * Copyright ©2018
 *
 * @author Iliya Kaslana
 * @version 1.0
 * @date 2018/9/17 14:01
 * @since 1.8
 */
public interface BookDao extends BaseDao<Book> {

    /**
     * 获得推荐的书单。
     * 要求返回六本书籍
     * 按最新的分享时间来
     * @return 首页的推荐书籍数据
     */
    public List<Book> findByPublishTime();

    /**
     * 根据书名关键字查询书籍
     * @param dto 查询条件
     * @return 对应书籍
     */
    public List<Book> findBookByName(SearchBookDto dto);

    /**
     * 根据书名关键字查询书籍
     * 按新旧程度排序
     * @param dto 查询条件
     * @return 对应书籍
     */
    public List<Book> findBookByNameAndNew(SearchBookDto dto);

    /**
     * 根据书名关键字查询书籍
     * 按照价格排序
     * @param dto 查询条件
     * @return 对应书籍
     */
    public List<Book> findBookByNameAndPrice(SearchBookDto dto);

    /**
     * 根据书类别查询书籍
     * @param dto 查询条件
     * @return 对应书籍
     */
    public List<Book> findBookByClassify(SearchBookDto dto);

    /**
     * 根据书类别查询书籍
     * 按新旧程度排序
     * @param dto 查询条件
     * @return 对应书籍
     */
    public List<Book> findBookByClassifyAndNew(SearchBookDto dto);

    /**
     * 根据书类别查询书籍
     * 按照价格排序
     * @param dto 查询条件
     * @return 对应书籍
     */
    public List<Book> findBookByClassifyAndPrice(SearchBookDto dto);

    /**
     * 添加书籍的时候同时添加书籍的所有人信息
     * @param book 书籍信息
     * @param userId 用户id
     */
    public void saveBookAndUser(Book book,String userId);

    public List<Book> findBooksByUserId(String userId);
}
