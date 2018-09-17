package com.yuan.iliya.rshare.book.entity.po;

import org.hibernate.annotations.Formula;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * All Rights Reserved, Designed By Iliya Kaslana
 * Copyright ©2018
 *
 * @author Iliya Kaslana
 * @version 1.0
 * @date 2018/9/17 13:45
 * @since 1.8
 */
@Entity
@Table(name = "tb_book")
public class Book implements Serializable {

    /**
     * 书籍ID
     */
    private String id;

    /**
     * 书籍名称
     */
    private String name;

    /**
     * 书籍的旧价格
     */
    private Double oldPrice;

    /**
     * 书籍的新价格
     */
    private Double newPrice;

    /**
     * 书籍的图片存放的url
     */
    private List<String> imgUrls;

    /**
     * 书籍的类别
     */
    private String classify;

    /**
     * 书籍在库存中还剩几本
     */
    private Integer remainNumber;

    /**
     * 书籍的发布共享时间
     */
    private Date publishTime;

    /**
     * 书籍的出版社
     */
    private String press;

    /**
     * 书籍的详细说明
     */
    private String detail;

    /**
     * 书籍的新旧属性
     */
    private Integer old;

    public Book(String id) {
        this.id = id;
    }

    public Book() {
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", oldPrice=" + oldPrice +
                ", newPrice=" + newPrice +
                ", imgUrls=" + imgUrls +
                ", classify='" + classify + '\'' +
                ", remainNumber=" + remainNumber +
                ", publishTime=" + publishTime +
                ", press='" + press + '\'' +
                ", detail='" + detail + '\'' +
                ", old=" + old +
                '}';
    }

    @Column(name = "book_detail",length = 255)
    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Column(name = "book_old",nullable = false)
    public Integer getOld() {
        return old;
    }

    public void setOld(Integer old) {
        this.old = old;
    }

    @Id
    @GenericGenerator(strategy = "uuid",name = "book_uuid")
    @GeneratedValue(generator = "book_uuid")
    @Column(name = "book_id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "book_name",length = 60,nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "book_old_price")
    public Double getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(Double oldPrice) {
        this.oldPrice = oldPrice;
    }

    @Formula("book_old_price * book_old / 10")
    public Double getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(Double newPrice) {
        this.newPrice = newPrice;
    }

    @ElementCollection(fetch = FetchType.EAGER)
    public List<String> getImgUrls() {
        return imgUrls;
    }

    public void setImgUrls(List<String> imgUrls) {
        this.imgUrls = imgUrls;
    }

    @Column(name = "book_classify")
    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    @Column(name = "book_remain_number")
    public Integer getRemainNumber() {
        return remainNumber;
    }

    public void setRemainNumber(Integer remainNumber) {
        this.remainNumber = remainNumber;
    }

    @Column(name = "book_publish_Time")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    @Column(name = "book_press")
    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }
}
