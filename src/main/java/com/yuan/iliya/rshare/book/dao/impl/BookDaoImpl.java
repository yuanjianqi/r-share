package com.yuan.iliya.rshare.book.dao.impl;

import com.yuan.iliya.rshare.book.dao.BookDao;
import com.yuan.iliya.rshare.book.entity.dto.SearchBookDto;
import com.yuan.iliya.rshare.book.entity.po.Book;
import com.yuan.iliya.rshare.book.entity.po.UserShareBook;
import com.yuan.iliya.rshare.core.dao.impl.HibernateBaseDaoImpl;
import com.yuan.iliya.rshare.core.util.HibernateUtil;
import com.yuan.iliya.rshare.user.entity.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.ArrayList;
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
@Repository("bookDao")
public class BookDaoImpl extends HibernateBaseDaoImpl<Book> implements BookDao {

    /**
     * 获得推荐的书单。
     * 要求返回六本书籍
     * 按最新的分享时间来
     *
     * @return 首页的推荐书籍数据
     */
    @Override
    public List<Book> findByPublishTime() {
        Session session = HibernateUtil.getCurrentSession(getSessionFactory());
        Query query = session.createQuery("from Book where remainNumber != 0 order by publishTime desc ");
        query.setMaxResults(6);

        return query.getResultList();
    }

    /**
     * 根据书名关键字查询书籍
     *
     * @param dto 查询条件
     * @return 对应书籍
     */
    @Override
    public List<Book> findBookByName(SearchBookDto dto) {
        Session session = HibernateUtil.getCurrentSession(getSessionFactory());
        String bookName = "%" + dto.getTitle() + "%";
        Query query = session.createQuery("from Book where name like :bookName");
        query.setParameter("bookName",bookName);
        query.setFirstResult(dto.getIndex());
        query.setMaxResults(dto.getCount());
        return query.getResultList();
    }

    /**
     * 根据书类别查询书籍
     *
     * @param dto 查询条件
     * @return 对应书籍
     */
    @Override
    public List<Book> findBookByClassify(SearchBookDto dto) {
        Session session = HibernateUtil.getCurrentSession(getSessionFactory());
        Query query = session.createQuery("from Book where classify = :classify");
        query.setParameter("classify",dto.getClassify());
        query.setFirstResult(dto.getIndex());
        query.setMaxResults(dto.getCount());
        return query.getResultList();
    }

    /**
     * 根据书名关键字查询书籍
     * 按新旧程度排序
     *
     * @param dto 查询条件
     * @return 对应书籍
     */
    @Override
    public List<Book> findBookByNameAndNew(SearchBookDto dto) {
        Session session = HibernateUtil.getCurrentSession(getSessionFactory());
        String bookName = "%" + dto.getTitle() + "%";
        Query query = session.createQuery("from Book where name like :bookName order by old desc ");
        query.setParameter("bookName",bookName);
        query.setFirstResult(dto.getIndex());
        query.setMaxResults(dto.getCount());
        return query.getResultList();
    }

    /**
     * 根据书名关键字查询书籍
     * 按照价格排序
     *
     * @param dto 查询条件
     * @return 对应书籍
     */
    @Override
    public List<Book> findBookByNameAndPrice(SearchBookDto dto) {
        Session session = HibernateUtil.getCurrentSession(getSessionFactory());
        String bookName = "%" + dto.getTitle() + "%";
        Query query = session.createQuery("from Book where name like :bookName order by newPrice asc ");
        query.setParameter("bookName",bookName);
        query.setFirstResult(dto.getIndex());
        query.setMaxResults(dto.getCount());
        return query.getResultList();
    }

    /**
     * 根据书类别查询书籍
     * 按新旧程度排序
     *
     * @param dto 查询条件
     * @return 对应书籍
     */
    @Override
    public List<Book> findBookByClassifyAndNew(SearchBookDto dto) {
        Session session = HibernateUtil.getCurrentSession(getSessionFactory());
        Query query = session.createQuery("from Book where classify = :classify order by old desc ");
        query.setParameter("classify",dto.getClassify());
        query.setFirstResult(dto.getIndex());
        query.setMaxResults(dto.getCount());
        return query.getResultList();
    }

    /**
     * 根据书类别查询书籍
     * 按照价格排序
     *
     * @param dto 查询条件
     * @return 对应书籍
     */
    @Override
    public List<Book> findBookByClassifyAndPrice(SearchBookDto dto) {
        Session session = HibernateUtil.getCurrentSession(getSessionFactory());
        Query query = session.createQuery("from Book where classify = :classify order by newPrice asc ");
        query.setParameter("classify",dto.getClassify());
        query.setFirstResult(dto.getIndex());
        query.setMaxResults(dto.getCount());
        return query.getResultList();
    }

    /**
     * 添加书籍的时候同时添加书籍的所有人信息
     *
     * @param book   书籍信息
     * @param userId 用户id
     */
    @Override
    public void saveBookAndUser(Book book, String userId) {
        Session session = HibernateUtil.getCurrentSession(getSessionFactory());
        Serializable id = session.save(book);
        UserShareBook userShareBook = new UserShareBook(new User(userId), new Book((String) id));
        session.save(userShareBook);
    }

    @Override
    public List<Book> findBooksByUserId(String userId) {
        Session session = HibernateUtil.getCurrentSession(getSessionFactory());
        Query query = session.createQuery("from UserShareBook where user.id = :id").setParameter("id", userId);
        List<UserShareBook> list = query.getResultList();
        List<Book> books = new ArrayList<>();
        for (UserShareBook shareBook : list){
            books.add(shareBook.getBook());
        }
        return books;
    }
}
