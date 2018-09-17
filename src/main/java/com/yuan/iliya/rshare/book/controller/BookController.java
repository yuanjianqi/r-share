package com.yuan.iliya.rshare.book.controller;

import com.yuan.iliya.rshare.book.entity.dto.SearchBookDto;
import com.yuan.iliya.rshare.book.entity.po.Book;
import com.yuan.iliya.rshare.book.entity.vo.BookRecommendVo;
import com.yuan.iliya.rshare.book.service.BookService;
import com.yuan.iliya.rshare.core.entity.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * All Rights Reserved, Designed By Iliya Kaslana
 * Copyright ©2018
 *
 * @author Iliya Kaslana
 * @version 1.0
 * @date 2018/9/17 14:06
 * @since 1.8
 */
@Controller("bookController")
@RequestMapping("/r-share")
@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/book")
    public Status addBook(@RequestBody Book book,@RequestParam("userId")String userId){
        Status status = new Status();

        try {
            bookService.save(book,userId);
            status.setStatus("200");
            status.setMessage("保存成功");
        }catch (Exception e){
            status.setStatus("500");
            status.setMessage("保存失败");
        }
        return status;
    }

    @PutMapping("/book/{id}")
    public Status updateBook(@PathVariable("id")String id,@RequestBody Book book){
        Status status = new Status();

        try {
            Book serviceBookById = bookService.findBookById(id);
            serviceBookById.setClassify(book.getClassify() == null? book.getClassify() : serviceBookById.getClassify());
            serviceBookById.setDetail(book.getDetail() == null? book.getDetail() : serviceBookById.getDetail());
            serviceBookById.setImgUrls(book.getImgUrls() == null? book.getImgUrls() : serviceBookById.getImgUrls());
            serviceBookById.setName(book.getName() == null? book.getName() : serviceBookById.getName());
            serviceBookById.setOld(book.getOld() == null? book.getOld() : serviceBookById.getOld());
            serviceBookById.setOldPrice(book.getOldPrice() == null? book.getOldPrice() : serviceBookById.getOldPrice());
            serviceBookById.setRemainNumber(book.getRemainNumber() == null? book.getRemainNumber() : serviceBookById.getRemainNumber());
            serviceBookById.setPress(book.getPress() == null? book.getPress() : serviceBookById.getPress());
            serviceBookById.setPublishTime(book.getPublishTime() == null? book.getPublishTime() : serviceBookById.getPublishTime());
            bookService.update(serviceBookById);
            status.setStatus("200");
            status.setMessage("修改成功");
        }catch (Exception e){
            status.setStatus("500");
            status.setMessage("修改失败");
        }
        return status;
    }

    @DeleteMapping("/book/{id}")
    public Status deleteBook(@PathVariable("id")String id){
        Status status = new Status();

        try {
            bookService.delete(id);
            status.setStatus("200");
            status.setMessage("删除成功");
        }catch (Exception e){
            status.setStatus("500");
            status.setMessage("删除失败");
        }
        return status;
    }
    @GetMapping("/book/{id}")
    public Book  getBookById(@PathVariable("id")String id){
        return bookService.findBookById(id);
    }

    @GetMapping("/books")
    public List<Book> getBooks(){
        return bookService.findBooks();
    }

    /**
     * 首页的推荐书目的接口
     * @return 书籍
     */
    @GetMapping("/book/recommend")
    public List<BookRecommendVo> getRecommendBooks(){
        return bookService.findByPublishTime();
    }

    /**
     * 按照传入的书的书关键词来搜索书籍
     * @return 书籍
     */
    @GetMapping("/book/search")
    public List<BookRecommendVo> searchBookByTitle(SearchBookDto searchBookDto){
        return bookService.findBySearchBookDto(searchBookDto);
    }

    /**
     * 按照传入的书的书关键词来搜索书籍
     * 按照新旧排序
     * @return 书籍
     */
    @GetMapping("/book/search/new")
    public List<BookRecommendVo> searchBookByTitleAndNew(SearchBookDto searchBookDto){
        searchBookDto.setByNew("new");
        return bookService.findBySearchBookDto(searchBookDto);
    }

    /**
     * 按照传入的书的书关键词来搜索书籍
     * 按照价格排序
     * @return 书籍
     */
    @GetMapping("/book/search/price")
    public List<BookRecommendVo> searchBookByTitleAndPrice(SearchBookDto searchBookDto){
        searchBookDto.setByPrice("price");
        return bookService.findBySearchBookDto(searchBookDto);
    }

    /**
     * 用户分享的书籍信息
     * @param userId 用户id
     * @return 用户分享的书籍
     */
    @GetMapping("/book/share/{id}")
    public List<BookRecommendVo> getUserShaerBooks(@PathVariable("id") String userId){
        return bookService.findBooksByUserId(userId);
    }

}
