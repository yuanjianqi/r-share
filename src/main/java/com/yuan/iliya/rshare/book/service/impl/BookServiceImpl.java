package com.yuan.iliya.rshare.book.service.impl;

import com.yuan.iliya.rshare.book.dao.BookDao;
import com.yuan.iliya.rshare.book.entity.dto.SearchBookDto;
import com.yuan.iliya.rshare.book.entity.po.Book;
import com.yuan.iliya.rshare.book.entity.vo.BookRecommendVo;
import com.yuan.iliya.rshare.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * All Rights Reserved, Designed By Iliya Kaslana
 * Copyright ©2018
 *
 * @author Iliya Kaslana
 * @version 1.0
 * @date 2018/9/17 14:04
 * @since 1.8
 */
@Service("bookService")
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;
    /**
     * 向数据库中存储指定的书籍
     *
     * @param book 书籍
     */
    @Override
    public String save(Book book,String userId) {
        return bookDao.saveBookAndUser(book,userId);
    }

    /**
     * 向数据库中更新指定书籍
     *
     * @param book 指定书籍
     */
    @Override
    public void update(Book book) {
        bookDao.update(book);
    }

    /**
     * 根据id来删除数据库中对应的书籍
     *
     * @param id 书籍id
     */
    @Override
    public void delete(Serializable id) {
        bookDao.delete(id);
    }

    /**
     * 根据id查询数据中对应的单个书籍
     *
     * @param id 书籍id
     */
    @Override
    public Book findBookById(Serializable id) {
        return bookDao.findObjectById(id);
    }

    /**
     * 查询数据库中的所有书籍
     *
     * @return 所有书籍
     */
    @Override
    public List<Book> findBooks() {
        return bookDao.findObjects();
    }

    /**
     * 获得推荐的书单。
     * 要求返回六本书籍
     * 按最新的分享时间来
     *
     * @return 首页的推荐书籍数据
     */
    @Override
    public List<BookRecommendVo> findByPublishTime() {

        List<Book> books = bookDao.findByPublishTime();
        return fillBookRecommendVo(books);
    }

    /**
     * 根据一定的条件查询书籍
     * 并做分页处理
     *
     * @param dto 查询条件
     * @return 推荐的书籍
     */
    @Override
    public List<BookRecommendVo> findBySearchBookDto(SearchBookDto dto) {

        if (dto.getIndex() == null){
            dto.setIndex(0);
        }

        if (dto.getCount() == null){
            dto.setCount(20);
        }

        //查找更多推荐
        if ("更多推荐".equals(dto.getClassify())){
            List<Book> books = bookDao.findObjectsByIndexAndSize(dto.getIndex(),dto.getCount());
            return fillBookRecommendVo(books);
        }

        //按照关键字查询书籍
        if (dto.getTitle() != null && dto.getClassify() == null && dto.getByNew() == null && dto.getByPrice() == null){
            List<Book> books = bookDao.findBookByName(dto);
            return fillBookRecommendVo(books);
        }

        //按照类别查询书籍
        if (dto.getTitle() == null && dto.getClassify() != null && dto.getByNew() == null && dto.getByPrice() == null){
            List<Book> books = bookDao.findBookByClassify(dto);
            return fillBookRecommendVo(books);
        }

        //按照关键字和新旧程度查询书籍
        if (dto.getTitle() != null && dto.getClassify() == null && "new".equals(dto.getByNew())&& dto.getByPrice() == null){
            List<Book> books = bookDao.findBookByNameAndNew(dto);
            return fillBookRecommendVo(books);
        }

        //按照关键字和价格查询书籍
        if (dto.getTitle() != null && dto.getClassify() == null && dto.getByNew() == null && "price".equals(dto.getByPrice())){
            List<Book> books = bookDao.findBookByNameAndPrice(dto);
            return fillBookRecommendVo(books);
        }

        //按照类别和新旧程度查询书籍
        if (dto.getTitle() == null && dto.getClassify() != null && "new".equals(dto.getByNew())&& dto.getByPrice() == null){
            List<Book> books = bookDao.findBookByClassifyAndNew(dto);
            return fillBookRecommendVo(books);
        }

        //按照类别和价格查询书籍
        if (dto.getTitle() == null && dto.getClassify() != null && dto.getByNew() == null && "price".equals(dto.getByPrice())){
            List<Book> books = bookDao.findBookByClassifyAndPrice(dto);
            return fillBookRecommendVo(books);
        }



        return null;
    }

    /**
     * 将Po转换成VO
     * @param books
     * @return
     */
    private List<BookRecommendVo> fillBookRecommendVo(List<Book> books){
        List<BookRecommendVo> bookRecommendVos = new ArrayList<>();
        for (Book book : books){
            BookRecommendVo vo = new BookRecommendVo();
            vo.setBookId(book.getId());
            vo.setBookName(book.getName());
            vo.setDescribe(book.getOld() + "成新");
            vo.setNewPrice(book.getNewPrice());
            vo.setOldPrice(book.getOldPrice());
            vo.setUrl(book.getImgUrls());
            bookRecommendVos.add(vo);
        }
        return bookRecommendVos;
    }

    /**
     * 根据用户id查找它共享的书籍
     *
     * @param userId 用户id
     * @return 用户共享的书籍
     */
    @Override
    public List<BookRecommendVo> findBooksByUserId(String userId) {
        List<Book> books = bookDao.findBooksByUserId(userId);
        List<BookRecommendVo> recommendVos = fillBookRecommendVo(books);
        return recommendVos;
    }

    /**
     * 批量删除书籍
     * @param bookIds 书籍的id
     */
    @Override
    public void deleteBookByIds(String[] bookIds) {
        bookDao.deleteBookByIds(bookIds);
    }

    /**
     * 上传图片保存
     * @param images 图片
     * @param path 要保存的路径
     * @param id 图片对应的bookid
     */
    @Override
    public void saveImage(MultipartFile[] images, String path, String id) {
        List<String> paths = new ArrayList<>();
        String filePath = null;
        String fileName = null;
        try {
            for (MultipartFile file : images){
                fileName = UUID.randomUUID().toString().replaceAll("-","") + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
                filePath = path + File.separator + fileName;
                file.transferTo(new File(filePath));
                paths.add("/upload/bookimage/" + fileName);
            }
            bookDao.saveImage(paths,id);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
